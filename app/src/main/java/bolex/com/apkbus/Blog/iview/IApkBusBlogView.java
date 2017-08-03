package bolex.com.apkbus.Blog.iview;

import java.util.List;

import bolex.com.apkbus.Blog.entity.ApkBusBlogItem;
import bolex.com.apkbus.Blog.entity.Banner;
import cn.wwah.basekit.base.iview.IBaseView;

/**
 */
public interface IApkBusBlogView extends IBaseView {

    void findBlogList(List<ApkBusBlogItem> mBlogs);
    void findHomeBanner(List<Banner> banners);
}