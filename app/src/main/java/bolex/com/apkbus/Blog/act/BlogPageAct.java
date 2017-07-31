package bolex.com.apkbus.Blog.act;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vise.log.ViseLog;

import java.util.ArrayList;
import java.util.List;

import bolex.com.apkbus.Blog.adpter.BlogListAdpter;
import bolex.com.apkbus.Blog.adpter.MyItemDecoration;
import bolex.com.apkbus.Blog.entity.ApkBusBlogItem;
import bolex.com.apkbus.Blog.iview.IApkBusBlogView;
import bolex.com.apkbus.Blog.presenter.ApkBusBlogPresenter;
import bolex.com.apkbus.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wwah.basekit.base.activity.BaseActivity;

/**
 * Created by liushenen on 2017/7/28.
 */

public class BlogPageAct extends BaseActivity implements IApkBusBlogView {

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
        ApkBusBlogPresenter mApkBusBlogPresenter = new ApkBusBlogPresenter(BlogPageAct.this, BlogPageAct.this);
        mApkBusBlogPresenter.findBlogList(APK_BLOG_TYPE, blogPage);

    }

    @Override
    public void findBlogList(List<ApkBusBlogItem> mBlogs) {
        apkBusBlogItems.addAll(mBlogs);
        blogListAdpter.notifyDataSetChanged();
        ViseLog.d(mBlogs);
    }
}
