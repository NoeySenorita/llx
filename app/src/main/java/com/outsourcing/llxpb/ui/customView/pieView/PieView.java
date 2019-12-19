package com.outsourcing.llxpb.ui.customView.pieView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.outsourcing.llxpb.R;

public class PieView extends View {
    private Paint paint;
    private RectF outRectF;
    private RectF centerRectF;
    private int w;
    private int h;
    private ViewData mViewData;
    private int underColor = Color.parseColor("#e5e5e5");
    private String mText;
    private int mNum;
    private int mForegroundColor;
    private float mPercentage;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PieView);
        mText = ta.getString(R.styleable.PieView_pText);
        mForegroundColor = ta.getColor(R.styleable.PieView_pColor, 0xFF666666);
        mPercentage = ta.getFloat(R.styleable.PieView_pPercent,0.0f);
        mPercentage = Math.min(mPercentage,1.0f);
        mNum = ta.getInteger(R.styleable.PieView_pNumber,0);
        ta.recycle();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        //设置画笔默认颜色
        paint.setColor(Color.WHITE);
        //设置画笔模式：填充
        paint.setStyle(Paint.Style.FILL);
        //初始化区域
        outRectF = new RectF();
        centerRectF = new RectF();
    }

    public void setViewData(ViewData mViewData) {
        this.mViewData = mViewData;
        initData();
    }

    private void initData() {
        if(null==mViewData){
            return;
        }
        mText = null == mViewData.name?mText:mViewData.name;
        mNum = mViewData.num;
        mPercentage = Math.min(mViewData.percentage,1.0f);
        invalidate();
    }

    //确定View大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;     //获取宽高
        this.h = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(w / 2, h / 2);             //将画布坐标原点移到中心位置
        float currentStartAngle = -90;
        float sweepAngle = 360*mPercentage;
        //起始角度
        float r = (float) (Math.min(w, h) / 2);     //饼状图半径(取宽高里最小的值)
        float gapWidth = r*0.15f;
        int textSize = (int) (r*0.35);
        paint.setTextAlign(Paint.Align.CENTER);
        String numText = mNum +"/"+ Math.round(mPercentage *100)+"%";
        float textX = 0;
        float textY = -r*0.125f;
        float numX = 0;
        float numY = textSize+10*0.125f;
        paint.setTextSize(textSize);
        outRectF.set(-r, -r, r, r);
        centerRectF.set(-r+gapWidth,-r+gapWidth,r-gapWidth,r-gapWidth);//设置将要用来画扇形的矩形的轮廓
        paint.setColor(underColor);
        canvas.drawArc(outRectF, currentStartAngle, 360-sweepAngle, true, paint);
        currentStartAngle += (360-sweepAngle);
        paint.setColor(mForegroundColor);
        canvas.drawArc(outRectF, currentStartAngle, sweepAngle, true, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(0,0,r-gapWidth,paint);
        paint.setColor(mForegroundColor);        //文字颜色
        canvas.drawText(mText, textX, textY, paint);
        paint.setTextSize((int) (r*0.3));
        canvas.drawText(numText, numX, numY, paint);
    }

}
