package bolex.com.apkbus.Blog.act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.transition.Slide;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elyeproj.loaderviewlibrary.LoaderTextView;

import bolex.com.apkbus.R;
import bolex.com.apkbus.View.GlideCircleTransform;
import bolex.com.apkbus.View.NestedWebView;
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
    NestedWebView llWeb;
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
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.appbar)
    CollapsingToolbarLayout appbar;


    final String BlogBaseUrl = "http://www.apkbus.com/";
    private String url;
    private WebSettings mWebSettings;
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
        llWeb.loadUrl(BlogBaseUrl + url);
        settings(llWeb);
        llWeb.setWebViewClient(mWebViewClient);
        llWeb.setWebChromeClient(mWebChromeClient);
    }

    boolean isNeedExe = true;
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if (newProgress > 95 && isNeedExe) {
                isNeedExe = !isNeedExe;
                view.loadUrl("javascript:function myFunction(){\n" +
                        "var $jquery = jQuery.noConflict();\n" +
                        "var content=$jquery('.article');\n" +
                        "$jquery('body').empty();\n" +
                        "content.css({\n" +
                        "background:\"#fff\",\n" +
                        "position:\"absolute\",\n" +
                        "top:\"0\",left:\"0\",\n" +
                        "});\n" +
                        "$jquery('body').append(content);\n" +
                        "$jquery(\"div\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");\n" +
                        "$jquery(\"a\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");\n" +
                        "$jquery(\"h1\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");\n" +
                        "$jquery(\"h2\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");\n" +
                        "$jquery(\"h3\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");\n" +
                        "$jquery(\"h4\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");\n" +
                        "$jquery(\"h5\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");\n" +
                        "$jquery(\"h6\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");\n" +
                        "$jquery(\"img\").removeAttr(\"class\").removeAttr(\"style\").removeAttr(\"id\");\n" +
                        "$jquery(\"img\").css({width: \"100%\",height:\"100%\",objecFit:\"cover\"});\n" +
                        "$jquery(\"h1\").css({paddingBottom: \"0.3em\",fontSize:\"2em\",borderBottom:\"1px solid #eaecef\"});\n" +
                        "$jquery(\"h2\").css({paddingBottom: \"0.3em\",fontSize:\"1.5em\",borderBottom:\"1px solid #eaecef\"});\n" +
                        "$jquery(\"h3\").css({fontSize:\"1.25em\"});\n" +
                        "$jquery(\"h4\").css({fontSize:\"1em\"});\n" +
                        "$jquery(\"h5\").css({fontSize:\"0.875em\"});\n" +
                        "$jquery(\"h6\").css({fontSize:\"0.85em\"});\n}");
                view.loadUrl("javascript:myFunction()");
                pDialog.cancel();
            }

            super.onProgressChanged(view, newProgress);
        }
    };
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(final WebView view, String url, Bitmap favicon) {
        }

        @Override
        public void onPageFinished(WebView view, String url) {
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
                    mWebSettings.setTextZoom(fontSize);
                }
                break;
            case R.id.tv_add:
                if (fontSize < 700) {
                    fontSize += 50;
                    mWebSettings.setTextZoom(fontSize);
                }
                break;
        }
    }


    private void setupWindowAnimations() {
        Slide slide = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            slide = new Slide();
            slide.setDuration(1000);
            getWindow().setExitTransition(slide);
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }


    private void settings(WebView webView) {
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setSavePassword(false);

        if (Build.VERSION.SDK_INT >= 21) {
            mWebSettings.setMixedContentMode(0);
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT < 19) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

//        mWebSettings.setRenderPriority(android.webkit.AgentWebSettings.RenderPriority.HIGH);
        mWebSettings.setTextZoom(fontSize);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setBlockNetworkImage(false);//是否阻塞加载网络图片  协议http or https
        mWebSettings.setAllowFileAccess(true); //允许加载本地文件html  file协议, 这可能会造成不安全 , 建议重写关闭
        mWebSettings.setAllowFileAccessFromFileURLs(false); //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
        mWebSettings.setAllowUniversalAccessFromFileURLs(false);//允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 19)
            mWebSettings.setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        else
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setNeedInitialFocus(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        mWebSettings.setDefaultFontSize(22);
        mWebSettings.setMinimumFontSize(16);//设置 WebView 支持的最小字体大小，默认为 8
        mWebSettings.setGeolocationEnabled(true);
        //适配5.0不允许http和https混合使用情况
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //缓存文件最大值
        mWebSettings.setAppCacheMaxSize(Long.MAX_VALUE);
    }
}
