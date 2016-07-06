package com.example.jin.popupview.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.jin.popupview.R;

/**
 * Created by Jin on 2016/7/4.
 */
public class PopView extends View {

    private int mWidth; //当前view的宽
    private int mHeight; //当前view的高
    private int mRectWidth;
    private int mRectHeight;
    private float mRectPercent = 0.8f;

    private String msg;

    private Rect aRect;

    public PopView(Context context) {
        super(context);
    }

    public PopView(Context context, AttributeSet attrs) {
        super(context, attrs);

        aRect = new Rect();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.customViewAttr);

        msg = ta.getString(R.styleable.customViewAttr_msg);

        ta.recycle();
    }

    public PopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PopView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize; //获取当前view的宽度
            mRectWidth = (int) (mWidth);
        }else if (widthMode == MeasureSpec.AT_MOST){
            //未确定宽高
            mWidth = widthSize; //获取当前view的宽度
            mRectWidth = (int) (mWidth);
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;//获取当前view的高度
            mRectHeight = (int) (mHeight * mRectPercent + 0.1);
        }else if (heightMode == MeasureSpec.AT_MOST){
            mHeight = heightSize;//获取当前view的高度
            mRectHeight = (int) (mHeight * mRectPercent + 0.1);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculateWH();
        Paint p = new Paint();
        p.setColor(Color.parseColor("#2C97DE"));
        p.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(new RectF(0, 0, mRectWidth, mRectHeight), 10, 10, p);

        Path path = new Path();
        path.moveTo(mRectWidth / 2 - 30, mRectHeight);
        path.lineTo(mRectWidth / 2, mRectHeight + 20);
        path.lineTo(mRectWidth / 2 + 30, mRectHeight);
        path.close();
        canvas.drawPath(path, p);

        float textWidth = aRect.width();
        float textHeight = aRect.height();

        Paint pp = new Paint();
        pp.setColor(Color.WHITE);
        pp.setStyle(Paint.Style.FILL);
        pp.setTextSize(40);
        canvas.drawText(msg,(getWidth()-getMeasuredWidth())/2,(getHeight()+textHeight)/2,pp);
    }


    public void setText(String msg){
        this.msg = msg;
    }

    public void calculateWH(){  //计算宽高
        int rows = 0;
        if(msg.length()>20){
            mRectWidth = (int) (mWidth * 0.8);
            mRectHeight = 60 + 40 * (msg.length()/20+1);

            if(msg.length()%20==0){
                rows = msg.length()/20;
                for (int i=0;i<rows;i++){
                    msg += msg.substring(20*i,20*(i+1))+"\\n";
                }
            }else{
                rows = msg.length()/20+1;
                for (int i=0;i<rows;i++){
                    if(i==(rows-1)){
//                        msg += msg.substring(20*i);
                        msg = "dadada"+"\r\n"+"fdsjfdf";
                    }else{
                        msg += msg.substring(20*i,20*(i+1))+"\\n";
                    }
                }
            }


        }
        Log.v("字符的长度",msg.length()+" ");
    }

    public void showTextStyles(){

    }
}
