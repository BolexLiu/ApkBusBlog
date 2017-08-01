package bolex.com.apkbus.Blog.act;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;

import bolex.com.apkbus.R;
import bolex.com.apkbus.base.Activity.BusBaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bolex on 2017/8/1.
 */

public class BlogDetailAct extends BusBaseActivity {
    @Bind(R.id.ll_root)
    LinearLayout llRoot;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.ll_web)
    LinearLayout llWeb;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.tv_sub)
    TextView tvSub;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    private AgentWeb mAgentWeb;

    final String BlogBaseUrl = "http://www.apkbus.com/";
    private String url;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_blog_detail);
        ButterKnife.bind(this);
        initDate();
        init();
        initEvent();
    }

    private void initDate() {
        url = getIntent().getStringExtra("url");

    }

    private void init() {
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(llWeb, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(receivedTitleCallback) //设置 Web 页面的 title 回调
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()//
                .ready()
                .go(BlogBaseUrl + url);
        webSettings = mAgentWeb.getAgentWebSettings().getWebSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {

            view.loadUrl("javascript:function myFunction(){var $jquery = jQuery.noConflict();var content=$jquery('.article');$jquery('body').empty();content.css({background:\"#fff\",position:\"absolute\",top:\"0\",left:\"0\"});$jquery('body').append(content);}");
            view.loadUrl("javascript:myFunction()");
            super.onPageFinished(view, url);
        }
    };

    ChromeClientCallbackManager.ReceivedTitleCallback receivedTitleCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            BlogDetailAct.this.title.setText(title);
        }
    };

    private void initEvent() {
    }

    int fontSize = 100;

    @OnClick({R.id.tv_sub, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sub:
                if (fontSize > 100) {
                    fontSize -= 50;
                    webSettings.setTextZoom(fontSize);
                }
                break;
            case R.id.tv_add:
                if (fontSize < 500) {
                    fontSize += 50;
                    webSettings.setTextZoom(fontSize);
                }
                break;
        }
    }

    private void swichTextSize(int fontSize) {
        switch (fontSize) {
            case 1:
                webSettings.setTextSize(WebSettings.TextSize.SMALLEST);

                break;
            case 2:
                webSettings.setTextSize(WebSettings.TextSize.SMALLER);
                break;
            case 3:
                webSettings.setTextSize(WebSettings.TextSize.NORMAL);
                break;
            case 4:
                webSettings.setTextSize(WebSettings.TextSize.LARGER);
                break;
            case 5:
                webSettings.setTextSize(WebSettings.TextSize.LARGEST);
                break;
        }
    }
}
