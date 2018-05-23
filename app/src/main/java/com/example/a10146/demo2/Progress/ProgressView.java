package com.example.a10146.demo2.Progress;

/**
 * Created by 10146 on 2018/5/21.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class ProgressView extends View{
    private int mProgress;
    private Paint mCurrentProgress;
    private Paint mMaxProgress;
    private Paint mPaintText;
    private int mWidth;
    private int mHeight;
    public int getmProgress() {
        return mProgress;
    }

    public void setmProgress(int mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }
    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCurrentProgress = new Paint();
        mCurrentProgress.setColor(Color.BLUE);
        mCurrentProgress.setAntiAlias(true);

        mCurrentProgress.setStrokeWidth(30);
        mCurrentProgress.setStyle(Paint.Style.STROKE);


        mMaxProgress = new Paint();
        mMaxProgress.setColor(Color.RED);
        mMaxProgress.setAntiAlias(true);
        mMaxProgress.setStrokeMiter(5);
        mMaxProgress.setStyle(Paint.Style.STROKE);

        mPaintText = new Paint();
        mPaintText.setColor(Color.BLACK);
        mPaintText.setStrokeWidth(5);
        mPaintText.setAntiAlias(true);
        mPaintText.setTextSize(40);
        mPaintText.setTextAlign(Align.CENTER);
    }

    public ProgressView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth=getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight= getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 140, mMaxProgress);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 110, mMaxProgress);
        RectF oval1=new RectF(mWidth / 2-125, mHeight / 2-125,mWidth / 2+125, mHeight / 2+125);
        canvas.drawArc( oval1, 0,mProgress/100f*360 ,false, mCurrentProgress);
        canvas.drawText(mProgress+"%", mWidth / 2, mHeight / 2+10,  mPaintText);

    }

}