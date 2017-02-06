package com.example.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Shader;

public class CircleProgressBar extends View {

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
	 * ��ǰ����
	 */
	private int mCurrentCount = 3;
	/**
	 * ����
	 */
	private int mCount;
	/**
	 * ÿ������ļ�϶
	 */
	private int mSplitSize;

	/**
	 * �Ƿ��������ı�
	 */
	private boolean isContinuity = true;

	/* Բ���߿� */
	private float circleBorderWidth = TypedValue
			.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources()
					.getDisplayMetrics());
	/* �ڱ߾� */
	private float circlePadding = TypedValue
			.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources()
					.getDisplayMetrics());
	/* �����С */
	private float textSize = TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 50, getResources().getDisplayMetrics());
	/* ����Բ�ܵĻ��� */
	private Paint mPaintFirst;
	/* ����Բ�ܰ�ɫ�ָ��ߵĻ��� */
	private Paint mPaintSecond;
	/* �������ֵĻ��� */
	private Paint textPaint;
	/* �ٷֱ� */
	private int percent = 0;
	/* ����Բ����ɫ���� */
	private int[] colorArray = new int[] { Color.GREEN,
			Color.parseColor("#fe751a"), Color.parseColor("#13be23"),
			Color.GREEN };

	public CircleProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.CircleProgressBar, defStyleAttr, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CircleProgressBar_firstCorlor:
				mFirstColor = a.getColor(attr, Color.GREEN);

				break;
			case R.styleable.CircleProgressBar_secondCorlor:
				mSecondColor = a.getColor(attr, Color.CYAN);

				break;
			case R.styleable.CircleProgressBar_circleWidth:
				mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20,
								getResources().getDisplayMetrics()));

				break;
			case R.styleable.CircleProgressBar_dotCount:
				mCount = a.getInt(attr, 20);

				break;
			case R.styleable.CircleProgressBar_splitSize:
				mSplitSize = a.getInt(attr, 20);

				break;

			case R.styleable.CircleProgressBar_continuity:
				isContinuity = a.getBoolean(attr, true);

				break;

			}

		}

		init();

	}

	public CircleProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircleProgressBar(Context context) {
		this(context, null);
	}

	private void init() {
		mPaintFirst = new Paint();
		mPaintFirst.setStyle(Paint.Style.STROKE);
		mPaintFirst.setAntiAlias(true);
		mPaintFirst.setColor(Color.LTGRAY);
		mPaintFirst.setStrokeWidth(mCircleWidth);// ����Բ���Ŀ��

		mPaintSecond = new Paint();
		mPaintSecond.setStyle(Paint.Style.STROKE);
		mPaintSecond.setAntiAlias(true);
		mPaintSecond.setColor(Color.LTGRAY);
		mPaintSecond.setStrokeWidth(mCircleWidth);

		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(textSize);
		textPaint.setColor(Color.BLACK);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
		int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(Math.min(measureWidth, measureHeight),
				Math.min(measureWidth, measureHeight));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawOval(canvas);
		drawText(canvas);

	}

	private void drawText(Canvas canvas) {
		float textWidth = textPaint.measureText(mCurrentCount * 100 / mCount
				+ "%");
		int textHeight = (int) Math.ceil(textPaint.getFontMetrics().descent
				- textPaint.getFontMetrics().ascent + 2);
		canvas.drawText(mCurrentCount * 100 / mCount + "%", getWidth() / 2
				- textWidth / 2, getHeight() / 2 + textHeight / 4, textPaint);
	}

	Matrix matrix;

	private void drawOval(Canvas canvas) {
		matrix = new Matrix();
		int center = getWidth() / 2;
		int radius = center - mCircleWidth / 2;
		// mCount��Բ�������ٿ飬mSplitSize��ÿ��ļ����itemSize��ÿ���Ӧ�ĽǶ�
		float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;
		// Բ�Ĵ�С
		RectF oval = new RectF(center - radius, center - radius, center
				+ radius, center + radius);
		mPaintFirst.setColor(mFirstColor);
		// ����һ����ɫ��Բ��
		for (int i = 0; i < mCount; i++) {
			canvas.drawArc(oval, (i) * (itemSize + mSplitSize) - 90, itemSize,
					false, mPaintFirst);
		}
		// ����ɨ�轥����ѡ��-90��
		SweepGradient sweepGradient = new SweepGradient(center, center,
				colorArray, null);
		matrix.setRotate(-90, center, center);
		sweepGradient.setLocalMatrix(matrix);
		mPaintSecond.setShader(sweepGradient);
		mPaintSecond.setShadowLayer(10, 10, 10, Color.RED);
		// ���ڶ���Բ��
		for (int i = 0; i < mCurrentCount; i++) {
			canvas.drawArc(oval, (i) * (itemSize + mSplitSize) - 90, itemSize,
					false, mPaintSecond);
		}
	}

	private int downY, upY, moveY;

	int tempY;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = (int) event.getY();

			tempY = downY;
			break;
		case MotionEvent.ACTION_UP:

			if (!isContinuity) {
				upY = (int) event.getY();
				if (downY > upY) {
					int cha = downY - upY;
					if (cha > 50) {
						up();
					}
				} else {
					int cha = upY - downY;
					if (cha > 50) {
						down();
					}
				}
			}

			tempY = 0;

			break;

		case MotionEvent.ACTION_MOVE:

			if (isContinuity) {// ���������仯
				moveY = (int) event.getY();

				if (moveY > tempY)// �»�
				{
					int cha = moveY - tempY;
					if (cha > 50) {
						down(cha / 50);
						tempY = moveY;
					}

				} else {
					int cha = tempY - moveY;
					if (cha > 50) {
						up(cha / 50);
						tempY = moveY;
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
		if (mCurrentCount > mCount) {
			mCurrentCount = mCount;
		}
		changeVolume();
	}

	/**
	 * ��ǰ����-1
	 */
	public void down(int a) {
		mCurrentCount -= a;
		if (mCurrentCount < 0) {
			mCurrentCount = 0;
		}
		changeVolume();
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

	private void changeVolume() {
		if (listener != null) {
			listener.volumeChange(mCurrentCount);
		}
		postInvalidate();
	}

	public interface OnVolumeChangeListener {
		void volumeChange(int level);
	}

	private OnVolumeChangeListener listener;

	public void setOnVolumeChangeListener(OnVolumeChangeListener listener) {
		this.listener = listener;
	}

}
