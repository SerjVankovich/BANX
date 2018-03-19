package com.example.sergey.myapplication;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by sergey on 16.03.2018.
 */
class MyWebView extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl("https://ru.stackoverflow.com");
        return true;
    }
}
