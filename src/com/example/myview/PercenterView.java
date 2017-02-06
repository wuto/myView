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
	 * �Ƿ��������ı�
	 */
	private boolean isContinuity = true;

	/**
	 * ����
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

		mPaint.setAntiAlias(true);// �������
		mPaint.setStrokeWidth(mCircleWidth);// ����Բ���Ŀ��
		mPaint.setStrokeCap(Paint.Cap.ROUND);// �����߶ζϵ���״ΪԲͷ
		mPaint.setAntiAlias(true); // �������
		mPaint.setStyle(Paint.Style.STROKE);// ���ÿ���
		int centre = getWidth() / 2;// ��ȡԲ�ĵ�x����
		int radius = centre - mCircleWidth / 2;// �뾶

		drawOval(canvas, centre, radius);
		/**
		 * �������������ε�λ��
		 */
		int relRadius = radius - mCircleWidth / 2;// �����Բ�İ뾶
		/**
		 * ���������εľ��붥�� = mCircleWidth + relRadius - ��2 / 2
		 */
		mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius)
				+ mCircleWidth;
		/**
		 * ���������εľ������ = mCircleWidth + relRadius - ��2 / 2
		 */
		mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius)
				+ mCircleWidth;
		mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
		mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

		/**
		 * ���ͼƬ�Ƚ�С����ô����ͼƬ�ĳߴ���õ�������
		 */
		if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
			mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f
					/ 2 - mImage.getWidth() * 1.0f / 2);
			mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage
					.getHeight() * 1.0f / 2);
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

		// spaceCountΪ�·�Ԥ�����οհף�������ܹ��հ׵Ļ���space
		float space = itemSize * spaceCount + mSplitSize * (spaceCount + 1);

		// Բ�Ĵ�С
		RectF oval = new RectF(centre - radius, centre - radius, centre
				+ radius, centre + radius); // ���ڶ����Բ������״�ʹ�С�Ľ���

		mPaint.setColor(mFirstColor); // ����Բ������ɫ
		for (int i = 0; i < mCount - spaceCount; i++) {
			canvas.drawArc(oval, i * (itemSize + mSplitSize) + 90 + space / 2,
					itemSize, false, mPaint); // ���ݽ��Ȼ�Բ��
		}

		mPaint.setColor(mSecondColor); // ����Բ������ɫ
		for (int i = 0; i < mCurrentCount; i++) {
			canvas.drawArc(oval, i * (itemSize + mSplitSize) + 90 + space / 2,
					itemSize, false, mPaint); // ���ݽ��Ȼ�Բ��
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
				if (xUp > xDown)// �»�
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

			if (isContinuity) {// ���������仯
				xMove = (int) event.getY();

				if (xMove > xTemp)// �»�
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
	 * ��ǰ����+1
	 */
	public void up() {
		up(1);
	}

	/**
	 * ��ǰ����-1
	 */
	public void down() {
		down(1);
	}

	/**
	 * ��ǰ����+1
	 */
	public void up(int a) {

		mCurrentCount += a;
		if (mCurrentCount > mCount - spaceCount) {
			mCurrentCount = mCount - spaceCount;
		}
		changePercent();
	}

	/**
	 * ��ǰ����-1
	 */
	public void down(int a) {
		mCurrentCount -= a;
		if (mCurrentCount < 0) {
			mCurrentCount = 0;
		}
		changePercent();
	}

	/**
	 * ���ðٷֱ�
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
