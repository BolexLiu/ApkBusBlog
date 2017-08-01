//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package bolex.com.apkbus.base.Activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import cn.wwah.basekit.base.iview.IBaseView;
import cn.wwah.common.ActivityManagerUtil;
import cn.wwah.common.DrawerToast;
import cn.wwah.common.JLog;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.zhy.autolayout.AutoLayoutActivity;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class BusBaseActivity extends AutoLayoutActivity implements ActivityLifecycleProvider, IBaseView {
    public ActivityManagerUtil activityManagerUtil;
    public Activity mActivity;
    public DrawerToast mToast;
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    public BusBaseActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = this;
        this.activityManagerUtil = ActivityManagerUtil.getInstance();
        this.activityManagerUtil.pushOneActivity(this);
        this.lifecycleSubject.onNext(ActivityEvent.CREATE);
//当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        this.mToast = DrawerToast.getInstance(this.getApplicationContext());
    }

    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return this.lifecycleSubject.asObservable();
    }

    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(this.lifecycleSubject, event);
    }

    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycle.bindActivity(this.lifecycleSubject);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @CallSuper
    protected void onStart() {
        super.onStart();
        this.lifecycleSubject.onNext(ActivityEvent.START);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == 2) {
            JLog.e("   现在是横屏");
        } else if (newConfig.orientation == 1) {
            JLog.e("   现在是竖屏");
        }

    }

    @CallSuper
    protected void onResume() {
        super.onResume();
        this.lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @CallSuper
    protected void onPause() {
        this.lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @CallSuper
    protected void onStop() {
        this.lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.activityManagerUtil.popOneActivity(this);
    }

    public void showLoading() {
    }

    public void hideLoading() {
    }

    public void showException(Throwable pe) {
    }
}
