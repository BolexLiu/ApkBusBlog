package bolex.com.apkbus.Blog.adpter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import bolex.com.apkbus.Blog.api.ApkBusBlog;
import bolex.com.apkbus.Blog.entity.ApkBusBlogItem;
import bolex.com.apkbus.R;

/**
 * Created by Bolex on 2017/7/30.
 */

public class BlogListAdpter extends BaseQuickAdapter<ApkBusBlogItem, BaseViewHolder> {

    public BlogListAdpter(ArrayList<ApkBusBlogItem> data) {
        super(R.layout.blog_item, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, ApkBusBlogItem item) {
        baseViewHolder.setText(R.id.tv_name, item.getAuthorName())
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getPreview())
                .setText(R.id.tv_time, item.getTime())
                .setText(R.id.tv_comment, "评论:" + item.getCommentCount())
                .setText(R.id.tv_like, "喜欢:" + item.getLikeCount());
    }
}
