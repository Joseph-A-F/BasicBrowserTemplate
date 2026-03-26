package edu.temple.basicbrowser

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var urlEditText: EditText
    private lateinit var goButton: ImageButton
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)

        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

        goButton.setOnClickListener {
            // if www. isn't in url. add it
            // if not https://
            // if  not .com add
            var url_text = urlEditText.text.toString()
            if (url_text.isBlank() || url_text.isEmpty()) return@setOnClickListener;
            url_text = fixURL(url_text)

            webView.loadUrl(url_text.toString());

        }

    }

    private fun fixURL(url_text: String): String {
        var url_text1 = url_text
        if (!url_text1.contains("www.")) {
            url_text1 = "www.$url_text1";
        }
        if (!url_text1.contains("https://")) {
            url_text1 = "https://$url_text1";
        }
        if (!url_text1.contains(".com")) {
            url_text1 = "$url_text1.com";
        }
        Log.d("URL fixed", url_text1)
        return url_text1
    }
}