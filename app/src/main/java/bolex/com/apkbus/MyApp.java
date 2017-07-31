package bolex.com.apkbus;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.vise.log.ViseLog;
import com.vise.log.inner.LogcatTree;

import cn.wwah.basekit.BaseKitApp;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 */
public class MyApp extends BaseKitApp {
    private String baseUrl = "http://www.apkbus.com/";
    private Retrofit retrofit;
    private static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        ViseLog.getLogConfig()
                .configAllowLog(true)//是否输出日志
                .configShowBorders(true)//是否排版显示
                .configTagPrefix("ViseLog")//设置标签前缀
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")//个性化设置标签，默认显示包名
                .configLevel(Log.VERBOSE);//设置日志最小输出级别，默认Log.VERBOSE
        ViseLog.plant(new LogcatTree());//添加打印日志信息到Logcat的树

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static MyApp getApp() {
        return myApp;
    }
}