package bolex.com.apkbus.Blog.entity;

/**
 * Created by liushenen on 2017/8/3.
 */

public class Banner {
    String url;
    String title;
    String imgUrl;

    public Banner(String url, String title, String imgUrl) {
        this.url = url;
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
