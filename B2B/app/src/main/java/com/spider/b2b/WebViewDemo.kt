package com.spider.b2b

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebViewDemo : AppCompatActivity() {
    private var webView: WebView? = null
    var activity: Activity? = null
  //  private var progDailog: ProgressDialog? = null
    @SuppressLint("NewApi")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        activity = this
       // progDailog = ProgressDialog.show(activity, "Loading", "Please wait...", true)
        //progDailog.setCancelable(false)
        webView = findViewById<View>(R.id.webView) as WebView
        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.loadWithOverviewMode = true
        webView!!.settings.useWideViewPort = true
        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                //progDailog.show()
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                //progDailog.dismiss()
            }
        }
        webView!!.loadUrl("https://www.payumoney.com/paybypayumoney/#/2D8E4C02042852C28DE8C6CCF089F74B/")
    }
}