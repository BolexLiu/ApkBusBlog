package bolex.com.apkbus.Blog.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vise.log.ViseLog;

import java.util.ArrayList;
import java.util.List;

import bolex.com.apkbus.Blog.adpter.BlogListAdpter;
import bolex.com.apkbus.Blog.adpter.MyItemDecoration;
import bolex.com.apkbus.Blog.entity.ApkBusBlogItem;
import bolex.com.apkbus.Blog.iview.IApkBusBlogView;
import bolex.com.apkbus.Blog.presenter.ApkBusBlogPresenter;
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
    private ArrayList<ApkBusBlogItem> apkBusBlogItems;
    private BlogListAdpter blogListAdpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_blog_page);
        ButterKnife.bind(this);
        init();
        initEvent();
    }

    private void init() {
        rvBlogList.setLayoutManager(new LinearLayoutManager(mActivity));
        rvBlogList.addItemDecoration(new MyItemDecoration(mActivity));
        apkBusBlogItems = new ArrayList<>();
        blogListAdpter = new BlogListAdpter(apkBusBlogItems);
        rvBlogList.setAdapter(blogListAdpter);
    }

    private void initEvent() {
        blogListAdpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, BlogDetailAct.class);
                intent.putExtra("url",apkBusBlogItems.get(position).getUrl());
                startActivity(intent);
            }
        });
        ApkBusBlogPresenter mApkBusBlogPresenter = new ApkBusBlogPresenter(BlogPageAct.this, BlogPageAct.this);
        mApkBusBlogPresenter.findBlogList(APK_BLOG_TYPE, blogPage);

    }

    @Override
    public void findBlogList(List<ApkBusBlogItem> mBlogs) {
        apkBusBlogItems.addAll(mBlogs);
        blogListAdpter.notifyDataSetChanged();
        ViseLog.d(mBlogs);
    }

    @Override
    public void getBlogDetails(String html) {

    }
}
