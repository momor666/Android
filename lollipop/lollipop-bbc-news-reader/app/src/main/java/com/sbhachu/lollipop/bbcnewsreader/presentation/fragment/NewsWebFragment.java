package com.sbhachu.lollipop.bbcnewsreader.presentation.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.sbhachu.lollipop.bbcnewsreader.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_news_web)
public class NewsWebFragment extends Fragment {

    public static final String TAG = NewsWebFragment.class.getSimpleName();

    public static final String NEWS_URL = "news_url";

    @ViewById(R.id.news_web_view)
    public WebView webView;

    @FragmentArg(NEWS_URL)
    public String newsUrl;

    public static NewsWebFragment_ newInstance(String url) {
        NewsWebFragment_ fragment = new NewsWebFragment_();
        Bundle args = new Bundle();
        args.putString(NEWS_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsWebFragment() {
    }

    @AfterViews
    public void afterViews() {
        webView.setDrawingCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

        webView.setWebViewClient(new BBCNewsWebClient());

        WebSettings genericWebViewSettings = webView.getSettings();
        genericWebViewSettings.setJavaScriptEnabled(true);
        genericWebViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        genericWebViewSettings.setAllowFileAccess(true);
        genericWebViewSettings.setSupportZoom(false);
        genericWebViewSettings.setBuiltInZoomControls(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(newsUrl);
            }
        }, 500);

    }

    @Override
    public void onDetach() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDetach();
    }

    private class BBCNewsWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }



}
