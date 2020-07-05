package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class PdfView extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        progressBar=findViewById(R.id.progresss);

        setTitle(getIntent().getStringExtra("name"));
        webView=findViewById(R.id.wv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true) ;
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setSupportZoom(true);


        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        String pdf = getIntent().getStringExtra("url");
        String url="";
        try {
            url= URLEncoder.encode(pdf,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" +url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);

            }
        });

       // String url="";


    }
    }
