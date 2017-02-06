package com.example.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class voidView extends View{
	
	
	/** 
     * 第一圈的颜色 
     */  
    private int mFirstColor;  
  
    /** 
     * 第二圈的颜色 
     */  
    private int mSecondColor;  
    /** 
     * 圈的宽度 
     */  
    private int mCircleWidth;  
    /** 
     * 中间的图片 
     */  
    private Bitmap mImage;  
    /** 
     * 每个块块间的间隙 
     */  
    private int mSplitSize;  
    /** 
     * 个数 
     */  
    private int mCount;  
    /** 
     * 当前进度 
     */  
    private int mCurrentCount = 3;  
    /** 
     * 空白格
     */  
    private int spaceCount = 4;  
    
	
    /** 
     * 画笔 
     */  
    private Paint mPaint; 
	private Rect mRect;
	
	public voidView(Context context) {
		this(context,null);
	}
	
	
	public voidView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public voidView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray a= context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVolumControlBar, defStyleAttr, 0);
		int n=a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr=a.getIndex(i);
			switch (attr) {
			case R.styleable.CustomVolumControlBar_firstCorlor:
				mFirstColor=a.getColor(attr, Color.GREEN);
				
				break;
			case R.styleable.CustomVolumControlBar_secondCorlor:
				mSecondColor=a.getColor(attr, Color.CYAN);
				
				break;
			case R.styleable.CustomVolumControlBar_bg:
				mImage=BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
				
				break;
			case R.styleable.CustomVolumControlBar_circleWidth:
				mCircleWidth=a.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
				
				break;
			case R.styleable.CustomVolumControlBar_dotCount:
				mCount =a.getInt(attr, 20);
				
				break;
			case R.styleable.CustomVolumControlBar_splitSize:
				mSplitSize = a.getInt(attr, 20); 
				
				break;
			case R.styleable.CustomVolumControlBar_spaceCount:
				spaceCount = a.getInt(attr, 4); 
				
				break;
			}
		}
		a.recycle();
		mPaint = new Paint();  
        mRect = new Rect();
		
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
			
		mPaint.setAntiAlias(true);// 消除锯齿 
		mPaint.setStrokeWidth(mCircleWidth);// 设置圆环的宽度  
		mPaint.setStrokeCap(Paint.Cap.ROUND);// 定义线段断电形状为圆头  
		 mPaint.setAntiAlias(true); // 消除锯齿
		 mPaint.setStyle(Paint.Style.STROKE);// 设置空心  
		 int centre =getWidth()/2;// 获取圆心的x坐标
		 int radius = centre-mCircleWidth/2;// 半径  
		 
		 drawOval(canvas, centre, radius); 
		 /** 
	         * 计算内切正方形的位置 
	         */  
	        int relRadius = radius - mCircleWidth / 2;// 获得内圆的半径  
	        /** 
	         * 内切正方形的距离顶部 = mCircleWidth + relRadius - √2 / 2 
	         */  
	        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;  
	        /** 
	         * 内切正方形的距离左边 = mCircleWidth + relRadius - √2 / 2 
	         */  
	        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;  
	        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);  
	        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius); 
	        
	        
	        
	        /** 
	         * 如果图片比较小，那么根据图片的尺寸放置到正中心 
	         */  
	        if (mImage.getWidth() < Math.sqrt(2) * relRadius)  
	        {  
	            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);  
	            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);  
	            mRect.right = (int) (mRect.left + mImage.getWidth());  
	            mRect.bottom = (int) (mRect.top + mImage.getHeight());  
	  
	        }  
	        // 绘图  
	        canvas.drawBitmap(mImage, null, mRect, mPaint);  
		  	
		
	}


	private void drawOval(Canvas canvas, int centre, int radius) {
		/** 
         * 根据需要画的个数以及间隙计算每个块块所占的比例*360 
         */  
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;  
        
      //spaceCount为下方预留几段空白，计算出总共空白的弧度space
		float space = itemSize * spaceCount + mSplitSize * (spaceCount + 1);
        
        //圆的大小
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限  
  
        mPaint.setColor(mFirstColor); // 设置圆环的颜色  
        for (int i = 0; i < mCount - spaceCount; i++)  
        {  
            canvas.drawArc(oval, i * (itemSize + mSplitSize) + 90 + space / 2, itemSize, false, mPaint); // 根据进度画圆弧  
        }  
  
        mPaint.setColor(mSecondColor); // 设置圆环的颜色  
        for (int i = 0; i < mCurrentCount; i++)  
        {  
            canvas.drawArc(oval, i * (itemSize + mSplitSize)+ 90 + space / 2, itemSize, false, mPaint); // 根据进度画圆弧  
        }  
		
	}

    /** 
     * 当前数量+1 
     */  
    public void up()  
    {  
        mCurrentCount++;  
        postInvalidate();  
    }  
  
    /** 
     * 当前数量-1 
     */  
    public void down()  
    {  
        mCurrentCount--;  
        postInvalidate();  
    }  
  
    private int xDown, xUp;  
  
    @Override  
    public boolean onTouchEvent(MotionEvent event)  
    {  
  
        switch (event.getAction())  
        {  
        case MotionEvent.ACTION_DOWN:  
            xDown = (int) event.getY();  
            break;  
  
        case MotionEvent.ACTION_UP:  
            xUp = (int) event.getY();  
            if (xUp > xDown&&mCurrentCount > 0)// 下滑  
            {  
                down();  
            } else if (xUp < xDown && mCurrentCount < mCount-spaceCount)  
            {  
                up();  
            }  
            break;  
        }  
  
        return true;  
    }  
	
}
