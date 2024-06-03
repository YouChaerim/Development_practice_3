package com.example.project_merge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Announcement extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement);

        ImageButton button_back = findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, MainActivity.class);
                startActivity(intent);

            }
        });

        webView = (WebView) findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                // 페이지 로딩 완료 후, 아랫부분만 보여주기 위해 스크롤 조정
//                view.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // 페이지의 높이에서 웹뷰 높이를 빼면 아래쪽만 보여집니다.
//                        view.scrollTo(0, 2100);
//                    }
//                },1); // 100ms 지연 후 실행, 필요한 경우 조정 가능
            }
        });

        webView.setWebChromeClient(new WebChromeClient());

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);

        webView.getSettings().setDomStorageEnabled(true);
        webView.scrollTo(0, 2100);

        webView.loadUrl("https://www.mpva.go.kr/icnc/selectBbsNttList.do?key=708&bbsNo=163");
    }
}
