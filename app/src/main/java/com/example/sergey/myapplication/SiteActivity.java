package com.example.sergey.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class SiteActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sites);

        webView = findViewById(R.id.web_view);
        progressBar = findViewById(R.id.progressBar);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebView() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
            }
        });
        webView.loadUrl(getIntent().getStringExtra("link"));



    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
