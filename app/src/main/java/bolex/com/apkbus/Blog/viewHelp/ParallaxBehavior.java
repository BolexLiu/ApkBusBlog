package bolex.com.apkbus.Blog.viewHelp;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.vise.log.ViseLog;

import cn.wwah.common.JLog;

/**
 * Created by Bolex on 2017/7/29.
 */


public class ParallaxBehavior extends CoordinatorLayout.Behavior<ImageView> {


    private ImageView chid;
    private View dependency;

    public ParallaxBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    float lastependencY = 0;
    boolean isDown = false;
    int childHeight = 0;
    int childWidth = 0;
    int dependencyHeight = 0;
    int dependencyWidth = 0;

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        this.chid = child;
        this.dependency = dependency;
        float dependencY = dependency.getY();
        ViseLog.d(dependency.getY());
        if (Math.abs(dependencY) == child.getHeight()) {
            lastependencY = dependencY;
            return true;
        }
        if (child.getWidth() != 0 && childHeight == 0) {
            childHeight = child.getHeight();
            childWidth = child.getWidth();
        }
        if (dependency.getWidth() != 0 && dependencyWidth == 0) {
            dependencyHeight = dependency.getHeight();
            dependencyWidth = dependency.getWidth();
        }
        if (dependencY == 0) {
            if (isDown) {
                ViewGroup.LayoutParams childLayoutParams = child.getLayoutParams();
                childLayoutParams.width = child.getWidth() + 1;
                childLayoutParams.height = child.getHeight() + 1;
                child.setLayoutParams(childLayoutParams);
                ViewGroup.LayoutParams dependencyLayoutParams = dependency.getLayoutParams();
                dependencyLayoutParams.width = dependency.getWidth() + 1;
                dependencyLayoutParams.height = dependency.getHeight() + 1;
                dependency.setLayoutParams(dependencyLayoutParams);
            } else {
                isDown = true;
            }
            return true;
        }
        if (lastependencY == 0) {
            lastependencY = dependencY;
        }
        float direction = lastependencY - dependencY;
        if (direction > 0) { //往上
            isDown = false;
            child.setY(child.getY() - direction / 2);
        } else {  //往下
            isDown = true;
            child.setY(child.getY() + Math.abs(direction / 2));
        }

        lastependencY = dependencY;
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, ImageView child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }


    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, ImageView child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);

        if (dependency != null) {
            isDown = false;
            ViewGroup.LayoutParams dependencyLayoutParams = dependency.getLayoutParams();
            dependencyLayoutParams.width = dependencyWidth;
            dependencyLayoutParams.height = dependencyHeight;
            dependency.setLayoutParams(dependencyLayoutParams);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            layoutParams.height = childHeight;
            layoutParams.width = childWidth;
            child.setLayoutParams(layoutParams);
        }


    }
}
