package com.example.sergey.myapplication;

import android.content.Context;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by sergey on 16.03.2018.
 */
abstract class MyWebView extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl("https://ru.stackoverflow.com");
        return true;
    }

    @Override
    public abstract void onPageFinished(WebView view, String url);
}
