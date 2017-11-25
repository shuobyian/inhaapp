package com.example.shuob.a171124;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        webView = (WebView) findViewById(R.id.inhawep);

        WebSettings set = webView.getSettings();
        set.setSupportMultipleWindows(true);
        set.setJavaScriptEnabled(true);
        set.getBuiltInZoomControls();
        set.setLightTouchEnabled(true);
        set.setSavePassword(true);
        set.setSaveFormData(true);

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("https://m.inha.ac.kr")) {
                    view.loadUrl(url);
                    return true;
                }
                else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                }
            }
        });
        webView.loadUrl("https://m.inha.ac.kr");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

