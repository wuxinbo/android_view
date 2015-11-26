package com.wu.UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.wu.android_view.R;

/**
 * Created by Administrator on 2015/4/7.
 */
public class CustomProgress extends View{
    private String tag="CustomProgress";
    private int Progress;
    private final static int maxprogress=360;
    /**
     * 设定圆环宽度
     */
    private float circleWidth;

    public float getCircleWidth() {
        return circleWidth;
    }

    public void setCircleWidth(float circleWidth) {
        this.circleWidth = circleWidth;
        invalidate();
    }

    public static int getMaxprogress() {
        return maxprogress;
    }

    public int getProgress() {
        return Progress;
    }

    public void setProgress(int progress) {
        Progress = progress;
        invalidate();
    }

    public CustomProgress(Context context) {
        super(context);
    }

    public CustomProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public CustomProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化控件。
     * @param context
     * @param attrs
     */
    public  void initView(Context context,AttributeSet attrs){
        if (attrs!=null){
            TypedArray array =context.obtainStyledAttributes(attrs,R.styleable.CustomProgress);
            Progress =array.getInt(R.styleable.CustomProgress_Progress,0);
            circleWidth =array.getFloat(R.styleable.CustomProgress_circleWidth,6);
            array.recycle();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint =new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(circleWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);//设置抗锯齿。
        canvas.drawCircle(getWidth()/2, getHeight()/2, 50, paint);
        RectF rec =new RectF((getWidth()/2)-50,(getHeight()/2)-50,(getWidth()/2)+50,(getHeight()/2)+50);
        paint.setColor(Color.WHITE);
        canvas.drawArc(rec, -90, Progress, false, paint);
        canvas.clipRect(rec);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);//获取控件高度
        int width = MeasureSpec.getSize(widthMeasureSpec);//获取控件宽度
        if (widthMode==MeasureSpec.AT_MOST||heightMode==MeasureSpec.AT_MOST){//判断模式为wrapcontent
            setMeasuredDimension(100+((int)circleWidth),100+((int)circleWidth));
        }else{
            setMeasuredDimension(width,height);
        }
    }
}
