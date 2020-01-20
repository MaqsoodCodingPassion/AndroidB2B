package com.spider.b2b.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.spider.b2b.R
import com.spider.b2b.productList.ProductListActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser != null && intent.extras ==null) {
            finish()
            startActivity(Intent(this,
                DashBoardActivity::class.java))
        }else if(auth!!.currentUser != null && intent.extras !=null){
            finish()
            startActivity(Intent(this,
                ProductListActivity::class.java))
        }
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        btn_signup!!.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }

        btn_reset_password!!.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ResetPasswordActivity::class.java))
        }

        btn_login!!.setOnClickListener(View.OnClickListener {
            val email = email!!.text.toString()
            val password = passwordField!!.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            progressBar!!.visibility = View.VISIBLE

            auth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@LoginActivity) { task ->
                    progressBar!!.visibility = View.GONE
                    if (!task.isSuccessful) {
                        // there was an error
                        if (password.length < 6) {
                            passwordField!!.error = getString(R.string.minimum_password)
                        } else {
                            Toast.makeText(this@LoginActivity, getString(R.string.auth_failed), Toast.LENGTH_LONG).show()
                        }
                    } else {
                        startActivity(Intent(this, DashBoardActivity::class.java))
                    }
                }
        })
    }
}
