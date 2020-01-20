package com.spider.b2b.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spider.b2b.R
import kotlinx.android.synthetic.main.flow_type_activity.*

class LoginFlowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flow_type_activity)

        btn_retailer.setOnClickListener{startActivity(Intent(this,
            LoginActivity::class.java))}
        btn_customers.setOnClickListener{
            var intent = Intent(this, LoginActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean("IS_CUSTOMER_FLOW",true)
            intent.putExtras(bundle)
            startActivity(intent)}
    }
}
