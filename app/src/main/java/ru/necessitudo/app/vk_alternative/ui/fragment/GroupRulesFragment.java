package ru.necessitudo.app.vk_alternative.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.necessitudo.app.vk_alternative.R;

/**
 * Created by olegdubrovin on 24/01/18.
 */

public class GroupRulesFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView mWebView;

    @BindString(R.string.screen_name_rules)
    String mTitle;


    public GroupRulesFragment() {

    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_rules;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getBaseActivity().getProgressBar().setVisibility(View.VISIBLE);

        mWebView.loadUrl(getString(R.string.group_rules));

        mWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                if (getBaseActivity() != null) {
                    getBaseActivity().getProgressBar().setVisibility(View.GONE);
                }
            }
        });
    }


}