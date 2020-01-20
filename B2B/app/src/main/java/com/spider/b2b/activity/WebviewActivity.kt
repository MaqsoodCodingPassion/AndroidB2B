package com.spider.b2b.activity

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.spider.b2b.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        /*  val wedData: String =  "<html><body><h1>Hello, Javatpoint!</h1></body></html>"
          val mimeType: String = "text/html"
          val utfType: String = "UTF-8"
          webView.loadData(wedData,mimeType,utfType)*/

        /* val myWebUrl: String = "file:///android_asset/index.html"
         webView.loadUrl(myWebUrl)*/

        webView.webViewClient = MyWebViewClient(this)
        //https://www.javatpoint.com/"
        var url:String = "https://www.payumoney.com/paybypayumoney/#/2D8E4C02042852C28DE8C6CCF089F74B/"
        webView.loadUrl(url)
    }
    class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }
    }
}