package com.example.webviewjava2;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. findViewById로 웹뷰 연결
        myWebView = (WebView) findViewById(R.id.wv);

        // 2. 웹뷰 설정 변경 (자바스크립트 허용 등)
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 네이버 등 현대적 웹사이트는 필수

        // 3. 현재 앱 안에서 웹페이지가 열리도록 설정
        myWebView.setWebViewClient(new WebViewClient());

        // 4. URL 로드
        myWebView.loadUrl("https://www.youtube.com");
    }
}