package bolex.com.apkbus.Blog.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;


public interface ApkBusBlog {
    @GET("plugin.php")
    Observable<ResponseBody> getBlogList(@Query("id") String id, @Query("page") int page);

    @GET
    Observable<ResponseBody> getBlogDetails(@Url String url);
}