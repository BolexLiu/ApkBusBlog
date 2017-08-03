package bolex.com.apkbus.Blog.act;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vise.log.ViseLog;
import com.yinglan.alphatabs.AlphaTabsIndicator;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import bolex.com.apkbus.Blog.adpter.BlogListAdpter;
import bolex.com.apkbus.Blog.viewHelp.MyItemDecoration;
import bolex.com.apkbus.Blog.entity.ApkBusBlogItem;
import bolex.com.apkbus.Blog.iview.IApkBusBlogView;
import bolex.com.apkbus.Blog.presenter.ApkBusBlogPresenter;
import bolex.com.apkbus.Blog.viewHelp.GlideImageLoader;
import bolex.com.apkbus.R;
import bolex.com.apkbus.base.Activity.BusBaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liushenen on 2017/7/28.
 */

public class BlogPageAct extends BusBaseActivity implements IApkBusBlogView {

    final String APK_BLOG_TYPE = "cxy_common_blog";
    int blogPage = 1;
    @Bind(R.id.rv_blog_list)
    RecyclerView rvBlogList;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.appbar)
    CollapsingToolbarLayout appbar;

    @Bind(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;
    private ArrayList<ApkBusBlogItem> apkBusBlogItems;
    private BlogListAdpter blogListAdpter;
    private ApkBusBlogPresenter mApkBusBlogPresenter;
    private LinearLayoutManager linearLayoutManager;
    List<String> images = new ArrayList<>();
    List<String> titles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_blog_page);
        ButterKnife.bind(this);
        init();
        initEvent();
    }



    private void init() {
        initBannerStyle();
        initBlogAdpter();
    }

    private void initBlogAdpter() {
        linearLayoutManager = new LinearLayoutManager(mActivity);
        rvBlogList.setLayoutManager(linearLayoutManager);
        rvBlogList.addItemDecoration(new MyItemDecoration(mActivity));
        apkBusBlogItems = new ArrayList<>();
        blogListAdpter = new BlogListAdpter(apkBusBlogItems);
        rvBlogList.setAdapter(blogListAdpter);
    }

    private void initBannerStyle() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ZoomOutSlide);

        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用

    }

    private void initEvent() {
        mApkBusBlogPresenter = new ApkBusBlogPresenter(BlogPageAct.this, BlogPageAct.this);
        mApkBusBlogPresenter.findBlogList(APK_BLOG_TYPE, blogPage);
        mApkBusBlogPresenter.findHomeBanner();

        //添加滑动监听

        blogListAdpter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mApkBusBlogPresenter.findBlogList(APK_BLOG_TYPE, blogPage);
            }
        }, rvBlogList);


    }

    @Override
    public void findBlogList(List<ApkBusBlogItem> mBlogs) {
        blogPage++;
        blogListAdpter.loadMoreComplete();
        apkBusBlogItems.addAll(mBlogs);
        blogListAdpter.notifyDataSetChanged();
        ViseLog.d(mBlogs);
    }

    @Override
    public void findHomeBanner(List<bolex.com.apkbus.Blog.entity.Banner> banners) {
        for (bolex.com.apkbus.Blog.entity.Banner banner : banners) {
            titles.add(banner.getTitle());
            images.add(banner.getImgUrl());
        }
        banner.setImages(images);
        banner.setBannerTitles(titles);
        banner.start();
        ViseLog.d(banners);
    }


    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
