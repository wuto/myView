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

public class PercenterView extends View {

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
	 * 是否开启连续改变
	 */
	private boolean isContinuity = true;

	/**
	 * 画笔
	 */
	private Paint mPaint;
	private Rect mRect;

	public PercenterView(Context context) {
		this(context, null);
	}

	public PercenterView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PercenterView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.CustomVolumControlBar, defStyleAttr, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CustomVolumControlBar_firstCorlor:
				mFirstColor = a.getColor(attr, Color.GREEN);

				break;
			case R.styleable.CustomVolumControlBar_secondCorlor:
				mSecondColor = a.getColor(attr, Color.CYAN);

				break;
			case R.styleable.CustomVolumControlBar_bg:
				mImage = BitmapFactory.decodeResource(getResources(),
						a.getResourceId(attr, 0));

				break;
			case R.styleable.CustomVolumControlBar_circleWidth:
				mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20,
								getResources().getDisplayMetrics()));

				break;
			case R.styleable.CustomVolumControlBar_dotCount:
				mCount = a.getInt(attr, 20);

				break;
			case R.styleable.CustomVolumControlBar_splitSize:
				mSplitSize = a.getInt(attr, 20);

				break;
			case R.styleable.CustomVolumControlBar_spaceCount:
				spaceCount = a.getInt(attr, 4);

				break;
			case R.styleable.CustomVolumControlBar_continuity:
				isContinuity = a.getBoolean(attr, true);
				
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
		int centre = getWidth() / 2;// 获取圆心的x坐标
		int radius = centre - mCircleWidth / 2;// 半径

		drawOval(canvas, centre, radius);
		/**
		 * 计算内切正方形的位置
		 */
		int relRadius = radius - mCircleWidth / 2;// 获得内圆的半径
		/**
		 * 内切正方形的距离顶部 = mCircleWidth + relRadius - √2 / 2
		 */
		mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius)
				+ mCircleWidth;
		/**
		 * 内切正方形的距离左边 = mCircleWidth + relRadius - √2 / 2
		 */
		mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius)
				+ mCircleWidth;
		mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
		mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

		/**
		 * 如果图片比较小，那么根据图片的尺寸放置到正中心
		 */
		if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
			mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f
					/ 2 - mImage.getWidth() * 1.0f / 2);
			mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage
					.getHeight() * 1.0f / 2);
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

		// spaceCount为下方预留几段空白，计算出总共空白的弧度space
		float space = itemSize * spaceCount + mSplitSize * (spaceCount + 1);

		// 圆的大小
		RectF oval = new RectF(centre - radius, centre - radius, centre
				+ radius, centre + radius); // 用于定义的圆弧的形状和大小的界限

		mPaint.setColor(mFirstColor); // 设置圆环的颜色
		for (int i = 0; i < mCount - spaceCount; i++) {
			canvas.drawArc(oval, i * (itemSize + mSplitSize) + 90 + space / 2,
					itemSize, false, mPaint); // 根据进度画圆弧
		}

		mPaint.setColor(mSecondColor); // 设置圆环的颜色
		for (int i = 0; i < mCurrentCount; i++) {
			canvas.drawArc(oval, i * (itemSize + mSplitSize) + 90 + space / 2,
					itemSize, false, mPaint); // 根据进度画圆弧
		}

	}

	private int xDown, xUp, xMove;

	int xTemp;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDown = (int) event.getY();
			xTemp = xDown;
			break;

		case MotionEvent.ACTION_UP:

			if (!isContinuity) {
				xUp = (int) event.getY();
				if (xUp > xDown)// 下滑
				{
					int cha = xUp - xDown;
					if (cha > 50) {
						down();
					}
				} else {
					int cha = xDown - xUp;
					if (cha > 50) {
						up();
					}
				}
			}

			xTemp = 0;
			break;

		case MotionEvent.ACTION_MOVE:

			if (isContinuity) {// 开启连续变化
				xMove = (int) event.getY();

				if (xMove > xTemp)// 下滑
				{
					int cha = xMove - xTemp;
					if (cha > 50) {
						down(cha / 50);
						xTemp = xMove;
					}

				} else {
					int cha = xTemp - xMove;
					if (cha > 50) {
						up(cha / 50);
						xTemp = xMove;
					}
				}
			}
			break;

		}

		return true;
	}

	/**
	 * 当前数量+1
	 */
	public void up() {
		up(1);
	}

	/**
	 * 当前数量-1
	 */
	public void down() {
		down(1);
	}

	/**
	 * 当前数量+1
	 */
	public void up(int a) {

		mCurrentCount += a;
		if (mCurrentCount > mCount - spaceCount) {
			mCurrentCount = mCount - spaceCount;
		}
		changePercent();
	}

	/**
	 * 当前数量-1
	 */
	public void down(int a) {
		mCurrentCount -= a;
		if (mCurrentCount < 0) {
			mCurrentCount = 0;
		}
		changePercent();
	}

	/**
	 * 设置百分比
	 * 
	 * @param percent
	 */
	public void setProgress(int progress) {
		float f = mCount * 1.0f / 100;
		mCurrentCount = (int) (f * progress);
		invalidate();
	}

	private void changePercent() {
		if (listener != null) {
			listener.percentChange(mCurrentCount);
		}
		postInvalidate();
	}

	public interface OnPercentChangeListener {
		void percentChange(int level);
	}

	private OnPercentChangeListener listener;

	public void setOnPercentChangeListener(OnPercentChangeListener listener) {
		this.listener = listener;
	}

}
