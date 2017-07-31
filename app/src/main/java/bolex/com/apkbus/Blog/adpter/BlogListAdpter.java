package bolex.com.apkbus.Blog.adpter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import bolex.com.apkbus.Blog.entity.ApkBusBlogItem;
import bolex.com.apkbus.View.GlideCircleTransform;
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
                .setText(R.id.tv_like, "喜欢:" + item.getLikeCount())
                .setText(R.id.tv_heat, "温度:" + item.getReadCount());
        Glide.with(mContext).
                load(item.getAuthorHeadUrl())
                .override(AutoUtils.getPercentWidthSize(100),AutoUtils.getPercentHeightSize(100))
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into((ImageView) baseViewHolder.getView(R.id.iv_head));

    }
}
