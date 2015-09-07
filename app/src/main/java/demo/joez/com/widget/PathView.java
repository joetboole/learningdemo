package demo.joez.com.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dell on 2015/9/8.
 */
public class PathView extends View{

    private Paint mPaint;
    private Path mPath;
    private float length;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(false);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
        mPath.moveTo(50, 50);
        mPath.lineTo(100, 400);
        mPath.lineTo(150, 50);
        mPath.lineTo(200, 400);
        mPath.lineTo(250, 50);

        PathMeasure measure = new PathMeasure(mPath,false);
        length = measure.getLength();
//        float[] intervals = new float[]{length,length};

        ObjectAnimator animator =ObjectAnimator.ofFloat(this,"phase",0.0f,1.0f);
        animator.setDuration(6000);
        animator.start();
    }

    public void setPhase(float phase){
        mPaint.setPathEffect(createPathEffect(length,phase));
        invalidate();
    }

    private static PathEffect createPathEffect(float length,float phase){
        return new DashPathEffect(new float[]{length,length},length - length * phase);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }
}
