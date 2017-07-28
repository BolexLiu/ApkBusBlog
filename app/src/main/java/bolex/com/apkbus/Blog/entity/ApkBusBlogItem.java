package bolex.com.apkbus.Blog.entity;

/**
 * Created by liushenen on 2017/7/28.
 */

public class ApkBusBlogItem {
    String authorHeadUrl;
    String authorName;
    String title;
    String preview;
    String time;
    String readCount;
    String commentCount;
    String likeCount;
    String url;

    public ApkBusBlogItem(String authorHeadUrl, String authorName, String title, String preview, String time, String readCount, String commentCount, String likeCount, String url) {
        this.authorHeadUrl = authorHeadUrl;
        this.authorName = authorName;
        this.title = title;
        this.preview = preview;
        this.time = time;
        this.readCount = readCount;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorHeadUrl() {
        return authorHeadUrl;
    }

    public void setAuthorHeadUrl(String authorHeadUrl) {
        this.authorHeadUrl = authorHeadUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }
}
