package com.wu.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import com.wu.android_view.R;

/**
 * Created by Administrator on 2015/4/7.
 */
public class CustomCircleProgress  extends View {

    public CustomCircleProgress(Context context) {
        super(context);
        initView(context);
    }

    public CustomCircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomCircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void initView(Context context){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.circle_progress);
        LinearInterpolator lir = new LinearInterpolator();//设置匀速动画。
        animation.setInterpolator(lir);
        startAnimation(animation);
    }
    private Handler updateTimeHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            CustomCircleProgress.this.invalidate();
        }
    };
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint =new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth((float) 6.0);
        paint.setStyle(Paint.Style.STROKE);
        RectF rec1 =new RectF((getWidth()/2)-50,(getHeight()/2)-50,(getWidth()/2)+50,(getHeight()/2)+50);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(6);
        canvas.drawArc(rec1, -90, 90, false, paint);
        canvas.clipRect(rec1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(106,106);
    }
}
