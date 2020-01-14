package com.example.harshmuski.shreemlogistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    // Button btn;
    // TextView txt;
    WebView wv;
    ProgressBar progressBar;
    String url = "http://fleet.shreemlogistics.com/login-2/?login=true&page=4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // btn = (Button) findViewById(R.id.button);
        // txt = (TextView) findViewById(R.id.editText);
        wv = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // wv.setWebViewClient(new MyBrowser());


        wv.setWebViewClient(new myWebClient(){
            @Override public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                wv.loadUrl("file:///android_asset/index.html");
            } });
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setDisplayZoomControls(false);
        wv.loadUrl(url);

    }
    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }



    public  class myWebClient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }



    }

}
