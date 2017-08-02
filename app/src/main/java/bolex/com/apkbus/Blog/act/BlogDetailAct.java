package bolex.com.apkbus.Blog.act;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.transition.Slide;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.just.library.AgentWeb;
import com.zhy.autolayout.utils.AutoUtils;

import bolex.com.apkbus.R;
import bolex.com.apkbus.View.GlideCircleTransform;
import bolex.com.apkbus.base.Activity.BusBaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Bolex on 2017/8/1.
 */

public class BlogDetailAct extends BusBaseActivity {
    @Bind(R.id.ll_root)
    LinearLayout llRoot;
    @Bind(R.id.ll_web)
    LinearLayout llWeb;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.tv_sub)
    TextView tvSub;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.tv_name)
    LoaderTextView tvName;
    private AgentWeb mAgentWeb;

    final String BlogBaseUrl = "http://www.apkbus.com/";
    private String url;
    private WebSettings webSettings;
    private String authorName;
    private String headUrl;
    private String title1;
    private SweetAlertDialog pDialog;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_blog_detail);
        ButterKnife.bind(this);
        init();
        intView();
        initDate();
        initEvent();
        setupWindowAnimations();
    }


    private void init() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        authorName = intent.getStringExtra("authorName");
        headUrl = intent.getStringExtra("headUrl");
        title1 = intent.getStringExtra("title");
        color = intent.getIntExtra("color", R.color.blue);
    }

    private void initDate() {
        llRoot.setBackgroundResource(color);
        Glide.with(mActivity).
                load(headUrl)
                .crossFade()
                .transform(new GlideCircleTransform(mActivity))
                .into((ivHead));
        title.setText(title1);
        tvName.setText(authorName);
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(llWeb, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()//
                .ready()
                .go(BlogBaseUrl + url);
        webSettings = mAgentWeb.getAgentWebSettings().getWebSettings();
        webSettings.setTextZoom(fontSize);
        webSettings.setJavaScriptEnabled(true);
    }


    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {

            view.loadUrl("javascript:function myFunction(){var $jquery = jQuery.noConflict();var content=$jquery('.article');$jquery('body').empty();content.css({background:\"#fff\",position:\"absolute\",top:\"0\",left:\"0\"});$jquery('body').append(content);$jquery(\"div\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");$jquery(\"a\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");$jquery(\"h1\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");$jquery(\"h2\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");$jquery(\"h3\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");$jquery(\"h4\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");$jquery(\"img\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");}");
            view.loadUrl("javascript:myFunction()");
            super.onPageFinished(view, url);
            pDialog.cancel();
        }
    };

    private void intView() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("智能排版中...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void initEvent() {
    }

    int fontSize = 200;

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
                if (fontSize < 700) {
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

    private void setupWindowAnimations() {
        Slide slide = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            slide = new Slide();
            slide.setDuration(1000);
            getWindow().setExitTransition(slide);
        }
    }
}
