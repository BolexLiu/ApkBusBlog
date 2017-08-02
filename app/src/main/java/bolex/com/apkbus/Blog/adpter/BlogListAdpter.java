package bolex.com.apkbus.Blog.adpter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import bolex.com.apkbus.Blog.act.BlogDetailAct;
import bolex.com.apkbus.Blog.entity.ApkBusBlogItem;
import bolex.com.apkbus.View.GlideCircleTransform;
import bolex.com.apkbus.R;

/**
 * Created by Bolex on 2017/7/30.
 */

public class BlogListAdpter extends BaseQuickAdapter<ApkBusBlogItem, BaseViewHolder> {
    int[] bg = {R.color.blue,
            R.color.lime,
            R.color.orange,
            R.color.green,
            R.color.purple,
            R.color.brown,
            R.color.teal};

    public BlogListAdpter(ArrayList<ApkBusBlogItem> data) {
        super(R.layout.blog_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final ApkBusBlogItem item) {
        baseViewHolder.setBackgroundRes(R.id.ll_blog_item, bg[baseViewHolder.getLayoutPosition() % 7]);
        baseViewHolder.setText(R.id.tv_name, item.getAuthorName())
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getPreview())
                .setText(R.id.tv_time, item.getTime())
                .setText(R.id.tv_comment, item.getCommentCount())
                .setText(R.id.tv_like, item.getLikeCount())
                .setText(R.id.tv_heat, item.getReadCount());
        Glide.with(mContext).
                load(item.getAuthorHeadUrl())
                .override(AutoUtils.getPercentWidthSize(200), AutoUtils.getPercentHeightSize(200))
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into((ImageView) baseViewHolder.getView(R.id.iv_head));

        baseViewHolder.getView(R.id.ll_blog_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BlogDetailAct.class);
                intent.putExtra("authorName", item.getAuthorName());
                intent.putExtra("headUrl", item.getAuthorHeadUrl());
                intent.putExtra("color", bg[baseViewHolder.getLayoutPosition() % 7]);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("url", item.getUrl());

                if (Build.VERSION.SDK_INT >= 21) {
                    // 使用api11 新加 api的方法
                    String ll_item = view.getContext().getString(R.string.ll_item);
                    String author_Name = view.getContext().getString(R.string.author_Name);
                    String iv_head = view.getContext().getString(R.string.iv_head);
                    String tv_title = view.getContext().getString(R.string.tv_title);
                    ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(),
                            Pair.create(view, ll_item),
                            Pair.create(baseViewHolder.getView(R.id.tv_name), author_Name),
                            Pair.create(baseViewHolder.getView(R.id.iv_head), iv_head),
                            Pair.create(baseViewHolder.getView(R.id.tv_title), tv_title));
                    view.getContext().startActivity(intent, transitionActivityOptions.toBundle());
                } else {
                    view.getContext().startActivity(intent);
                }
            }
        });
    }
}
