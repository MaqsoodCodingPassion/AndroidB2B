package com.spider.b2b.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.spider.b2b.R
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        auth = FirebaseAuth.getInstance()
        btn_back!!.setOnClickListener { finish() }
        btn_reset_password!!.setOnClickListener(View.OnClickListener {
            val email = email!!.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(application, "Enter your registered email id", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }

            progressBar!!.visibility = View.VISIBLE
            auth!!.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@ResetPasswordActivity, "We have sent you instructions to reset your password!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this@ResetPasswordActivity, "Failed to send reset email!", Toast.LENGTH_SHORT
                        ).show()
                    }

                    progressBar!!.visibility = View.GONE
                }
        })
    }
}
