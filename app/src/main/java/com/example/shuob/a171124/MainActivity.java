package com.example.shuob.a171124;

import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.inhawep);

        webView.setWebChromeClient(new TestWebChromeClient());
        WebSettings set = webView.getSettings();
        set.setJavaScriptEnabled(true);
        set.getBuiltInZoomControls();
        set.setLightTouchEnabled(true);
        set.setSavePassword(true);
        set.setSaveFormData(true);

        webView.loadUrl("https://m.inha.ac.kr");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }
    }

    private class TestWebChromeClient extends WebChromeClient {
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

            WebView.HitTestResult result = view.getHitTestResult();

            String url = result.getExtra();

            if (url != null && url.indexOf("___target=_blank") > -1) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }
    }
}
