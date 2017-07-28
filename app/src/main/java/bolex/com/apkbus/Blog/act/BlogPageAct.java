package bolex.com.apkbus.Blog.act;

import android.os.Bundle;

import com.vise.log.ViseLog;

import java.util.List;

import bolex.com.apkbus.Blog.entity.ApkBusBlogItem;
import bolex.com.apkbus.Blog.iview.IApkBusBlogView;
import bolex.com.apkbus.Blog.presenter.ApkBusBlogPresenter;
import bolex.com.apkbus.R;
import cn.wwah.basekit.base.activity.BaseActivity;

/**
 * Created by liushenen on 2017/7/28.
 */

public class BlogPageAct extends BaseActivity implements IApkBusBlogView {


  final String APK_BLOG_TYPE="cxy_common_blog";
    int blogPage=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEvent();
    }

    private void initEvent() {
        ApkBusBlogPresenter mApkBusBlogPresenter = new ApkBusBlogPresenter(BlogPageAct.this, BlogPageAct.this);
        mApkBusBlogPresenter.findBlogList(APK_BLOG_TYPE,blogPage);
    }

    @Override
    public void findBlogList(List<ApkBusBlogItem> mBlogs) {
        ViseLog.d(mBlogs);
    }
}
