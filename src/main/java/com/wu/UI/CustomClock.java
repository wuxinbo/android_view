package com.wu.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * 自定义时钟
 * Created by Administrator on 2015/4/9.
 */
public class CustomClock  extends View{
    private int radius =200;//半径
    private int centX,centY;//中心点坐标。
    private Calendar calendar ;
    private int hour ;//获取小时数
    private int minute;//获取分钟数。
    private int seconds;//获取秒数.
    private  static int scaleLength=20;
//    private int centRadius=10;//中心园的大小
    public CustomClock(Context context) {
        super(context);
    }

    public CustomClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomClock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化控件。
     */
    public  void initViewAttrs(){
        centX=getWidth()/2;//中心x坐标.
        centY=getHeight()/2;//中心y坐标.
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initViewAttrs();
        Paint paint =new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        drawscale(canvas, paint);//画刻度盘。
        paint.setStrokeWidth(3);//设置时分针的宽度。
//        drawCentCircle(canvas, paint);
        drawHour(canvas, paint);//画时针。
        drawMinute(canvas, paint);//画分针。
        paint.setStrokeWidth(1);
        drawSeconds(canvas, paint);//画秒针。

    }

    /**
     * 画中心园
     * @param canvas
     * @param paint
     */
    private void drawCentCircle(Canvas canvas, Paint paint) {
//        canvas.drawCircle(centX,centY,centRadius,paint);
    }

    /**
     * 绘制秒针。
     * @param canvas
     * @param paint
     */
    private void drawSeconds(Canvas canvas, Paint paint) {
        float x = (radius-scaleLength)*(float)Math.cos((seconds*6-90)*Math.PI/180)+centX;
        float y = (radius-scaleLength)*(float)Math.sin((seconds*6-90) * Math.PI / 180)+centY;
        canvas.drawLine(centX, centY, x, y, paint);
    }

    /**
     * 画分针。
     * @param canvas
     * @param paint
     */
    private void drawMinute(Canvas canvas, Paint paint) {
        float x = (radius-50)*(float)Math.cos((minute*6-90)*Math.PI/180)+centX;
        float y = (radius-50)*(float)Math.sin((minute*6-90) * Math.PI / 180)+centY;
        canvas.drawLine(centX, centY, x, y, paint);
    }

    /**
     * 初始化控件。
     */
    private void initView(Context context,AttributeSet attrs){
        Thread thread =new Thread(new UpdateTimeThread());
        thread.start();

    }

    /**
     * 绘制时针。
     * @param canvas
     * @param paint
     */
    public void drawHour(Canvas canvas,Paint paint){
        float x = (radius-100)*(float)Math.cos((hour*30+minute*6/12-90)*Math.PI/180)+centX;
        float y = (radius-100)*(float)Math.sin((hour*30+minute*6/12-90) * Math.PI / 180)+centY;
        canvas.drawLine(centX, centY, x, y, paint);
    }

    /**
     * 获取当前时间。
     */
    class UpdateTimeThread implements Runnable{
        @Override
        public void run() {
            while (true){
                calendar =Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR);//获取小时数
                minute = calendar.get(Calendar.MINUTE);
                seconds = calendar.get(Calendar.SECOND);
                updateTimeHandler.sendMessage(updateTimeHandler.obtainMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                calendar=null;
            }
        }
    }
    /**
     * 画时钟刻度函数。
     */
    private void drawscale(Canvas canvas,Paint paint){
        for (int i=0;i<60;i++){
            float x =radius*(float)Math.cos(i * 6 * Math.PI / 180)+centX;
            float y =radius*(float)Math.sin(i * 6 * Math.PI / 180)+centY;
            float x1=0,y1=0;
            if (i%5==0){ //画时刻度
                 x1=(radius-scaleLength)*(float)Math.cos(i * 6 * Math.PI / 180)+centX;
                 y1=(radius-scaleLength)*(float)Math.sin(i * 6 * Math.PI / 180)+centY;
            }else{
                 x1=(radius-5)*(float)Math.cos(i * 6 * Math.PI / 180)+centX;
                 y1=(radius-5)*(float)Math.sin(i * 6 * Math.PI / 180)+centY;
            }
            canvas.drawLine(x1,y1, x, y, paint);
        }
    }

    private Handler updateTimeHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            CustomClock.this.invalidate();
        }
    };
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(400,400);
    }
}
