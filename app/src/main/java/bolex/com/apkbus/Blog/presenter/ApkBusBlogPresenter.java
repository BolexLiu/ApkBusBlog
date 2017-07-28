package bolex.com.apkbus.Blog.presenter;

import android.content.Context;

import java.util.List;

import bolex.com.apkbus.Blog.entity.ApkBusBlogItem;
import bolex.com.apkbus.Blog.iview.IApkBusBlogView;
import bolex.com.apkbus.Blog.model.ApkBusBolgMode;
import cn.wwah.basekit.base.presenter.BasePresenter;
import rx.Observer;

/**
 */
public class ApkBusBlogPresenter extends BasePresenter {

    ApkBusBolgMode mApkbusBolgMode;
    IApkBusBlogView mIApkBusBlogView;
    private boolean isLodin = false;

    public ApkBusBlogPresenter(Context context, IApkBusBlogView mIApkBusBlogView) {
        super(context);
        this.mApkbusBolgMode = new ApkBusBolgMode(context);
        this.mIApkBusBlogView = mIApkBusBlogView;
    }


    @Override
    public Throwable doError(Throwable e) {
        return null;
    }

    public void findBlogList(String id, int page) {
        if (isLodin) {
            return;
        }
        mIApkBusBlogView.showLoading();
        isLodin = true;
        mApkbusBolgMode.findBlogList(id,page, new Observer<List<ApkBusBlogItem>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ApkBusBlogItem> mBlogs) {
                mIApkBusBlogView.findBlogList(mBlogs);
                isLodin = false;
            }
        });
    }




}