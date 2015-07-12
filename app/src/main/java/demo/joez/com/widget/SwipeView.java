package demo.joez.com.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by dell on 2015/7/12.
 */
public class SwipeView extends LinearLayout{
    private ViewDragHelper mDragHelper;

    public SwipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return Math.min(Math.max(left, -300), getPaddingLeft());
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return super.clampViewPositionVertical(child, top, dy);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
//                releasedChild.setTranslationX(0);

                super.onViewReleased(releasedChild, xvel, yvel);
                mDragHelper.smoothSlideViewTo(releasedChild,0,getPaddingTop());
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
       if(event.getAction() == MotionEvent.ACTION_MOVE){
           Log.e("debug", "left:" + getChildAt(0).getLeft());
            if(getChildAt(0).getLeft() < 0){
                getParent().requestDisallowInterceptTouchEvent(true);
            }else{
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return true;
    }
}
