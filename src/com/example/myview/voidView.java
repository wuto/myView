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
     * ��һȦ����ɫ 
     */  
    private int mFirstColor;  
  
    /** 
     * �ڶ�Ȧ����ɫ 
     */  
    private int mSecondColor;  
    /** 
     * Ȧ�Ŀ�� 
     */  
    private int mCircleWidth;  
    /** 
     * �м��ͼƬ 
     */  
    private Bitmap mImage;  
    /** 
     * ÿ������ļ�϶ 
     */  
    private int mSplitSize;  
    /** 
     * ���� 
     */  
    private int mCount;  
    /** 
     * ��ǰ���� 
     */  
    private int mCurrentCount = 3;  
    /** 
     * �հ׸�
     */  
    private int spaceCount = 4;  
    
	
    /** 
     * ���� 
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
			
		mPaint.setAntiAlias(true);// ������� 
		mPaint.setStrokeWidth(mCircleWidth);// ����Բ���Ŀ��  
		mPaint.setStrokeCap(Paint.Cap.ROUND);// �����߶ζϵ���״ΪԲͷ  
		 mPaint.setAntiAlias(true); // �������
		 mPaint.setStyle(Paint.Style.STROKE);// ���ÿ���  
		 int centre =getWidth()/2;// ��ȡԲ�ĵ�x����
		 int radius = centre-mCircleWidth/2;// �뾶  
		 
		 drawOval(canvas, centre, radius); 
		 /** 
	         * �������������ε�λ�� 
	         */  
	        int relRadius = radius - mCircleWidth / 2;// �����Բ�İ뾶  
	        /** 
	         * ���������εľ��붥�� = mCircleWidth + relRadius - ��2 / 2 
	         */  
	        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;  
	        /** 
	         * ���������εľ������ = mCircleWidth + relRadius - ��2 / 2 
	         */  
	        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;  
	        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);  
	        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius); 
	        
	        
	        
	        /** 
	         * ���ͼƬ�Ƚ�С����ô����ͼƬ�ĳߴ���õ������� 
	         */  
	        if (mImage.getWidth() < Math.sqrt(2) * relRadius)  
	        {  
	            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);  
	            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);  
	            mRect.right = (int) (mRect.left + mImage.getWidth());  
	            mRect.bottom = (int) (mRect.top + mImage.getHeight());  
	  
	        }  
	        // ��ͼ  
	        canvas.drawBitmap(mImage, null, mRect, mPaint);  
		  	
		
	}


	private void drawOval(Canvas canvas, int centre, int radius) {
		/** 
         * ������Ҫ���ĸ����Լ���϶����ÿ�������ռ�ı���*360 
         */  
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;  
        
      //spaceCountΪ�·�Ԥ�����οհף�������ܹ��հ׵Ļ���space
		float space = itemSize * spaceCount + mSplitSize * (spaceCount + 1);
        
        //Բ�Ĵ�С
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // ���ڶ����Բ������״�ʹ�С�Ľ���  
  
        mPaint.setColor(mFirstColor); // ����Բ������ɫ  
        for (int i = 0; i < mCount - spaceCount; i++)  
        {  
            canvas.drawArc(oval, i * (itemSize + mSplitSize) + 90 + space / 2, itemSize, false, mPaint); // ���ݽ��Ȼ�Բ��  
        }  
  
        mPaint.setColor(mSecondColor); // ����Բ������ɫ  
        for (int i = 0; i < mCurrentCount; i++)  
        {  
            canvas.drawArc(oval, i * (itemSize + mSplitSize)+ 90 + space / 2, itemSize, false, mPaint); // ���ݽ��Ȼ�Բ��  
        }  
		
	}

    /** 
     * ��ǰ����+1 
     */  
    public void up()  
    {  
        mCurrentCount++;  
        postInvalidate();  
    }  
  
    /** 
     * ��ǰ����-1 
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
            if (xUp > xDown&&mCurrentCount > 0)// �»�  
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
