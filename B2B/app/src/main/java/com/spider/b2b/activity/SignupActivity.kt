package com.spider.b2b.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.spider.b2b.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        btn_reset_password!!.setOnClickListener {
            startActivity(Intent(this@SignupActivity, ResetPasswordActivity::class.java))
        }

        sign_in_button!!.setOnClickListener { finish()}

        sign_up_button!!.setOnClickListener(View.OnClickListener {
            val email = email!!.text.toString().trim { it <= ' ' }
            val password = passwordField!!.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(
                    applicationContext,
                    "Password too short, enter minimum 6 characters!",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }

            progressBar!!.visibility = View.VISIBLE
            //create user
            auth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@SignupActivity) { task ->
                    Toast.makeText(
                        this@SignupActivity,
                        "createUserWithEmail:onComplete:" + task.isSuccessful,
                        Toast.LENGTH_SHORT
                    ).show()
                    progressBar!!.visibility = View.GONE
                    if (!task.isSuccessful) {
                        Toast.makeText(
                            this@SignupActivity, "Authentication failed." + task.exception!!,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@SignupActivity, "Launch Dash board screen ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        })
    }

    override fun onResume() {
        super.onResume()
        progressBar!!.visibility = View.GONE
    }
}