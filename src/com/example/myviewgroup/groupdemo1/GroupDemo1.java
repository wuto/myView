package com.example.myviewgroup.groupdemo1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class GroupDemo1 extends ViewGroup {

	public GroupDemo1(Context context) {
		this(context, null);
	}

	public GroupDemo1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GroupDemo1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/**
		 * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
		 */
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		// 计算出所有的childView的宽和高
		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int cCount = getChildCount();

		/**
		 * 记录如果是wrap_content是设置的宽和高
		 */
		int width = 0;
		int height = 0;

		int cWidth = 0;
		int cHeight = 0;
		MarginLayoutParams cParams = null;

		// 用于计算左边两个childView的高度
		int lHeight = 0;
		// 用于计算右边两个childView的高度，最终高度取二者之间大值
		int rHeight = 0;

		// 用于计算上边两个childView的宽度
		int tWidth = 0;
		// 用于计算下面两个childiew的宽度，最终宽度取二者之间大值
		int bWidth = 0;

		for (int i = 0; i < cCount; i++) {

			View childView = getChildAt(i);
			cWidth = childView.getMeasuredWidth();
			cHeight = childView.getMeasuredHeight();

			cParams = (MarginLayoutParams) childView.getLayoutParams();

			if (i == 0 || i == 1) {
				tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
			}

			if (i == 2 || i == 3) {
				bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
			}

			if (i == 0 || i == 2) {
				lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
			}

			if (i == 1 || i == 3) {
				rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
			}

			width = Math.max(tWidth, bWidth);
			height = Math.max(lHeight, rHeight);

			/**
			 * 如果是wrap_content设置为我们计算的值 否则：直接设置为父容器计算的值
			 */
			setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize
					: width, (heightMode == MeasureSpec.EXACTLY) ? heightSize
					: height);

		}

	}

	@Override
	protected void onLayout(boolean arg0, int l, int t, int r, int b) {

		int cCount = getChildCount();
		int cWidth = 0;
		int cHeight = 0;
		MarginLayoutParams cParams = null;

		/**
		 * 遍历所有childView根据其宽和高，以及margin进行布局
		 */
		for (int i = 0; i < cCount; i++) {
			View childView = getChildAt(i);
			cWidth = childView.getMeasuredWidth();
			cHeight = childView.getMeasuredHeight();
			cParams = (MarginLayoutParams) childView.getLayoutParams();

			int cl = 0, ct = 0, cr = 0, cb = 0;

			switch (i) {
			case 0:
				cl=cParams.leftMargin;
				ct=cParams.topMargin;
				break;
			case 1:
				cl=getWidth()-cWidth-cParams.rightMargin;
				ct=cParams.topMargin;
				break;
			case 2:
				cl=cParams.leftMargin;
				ct=getHeight()-cHeight-cParams.bottomMargin;
				break;
			case 3:
				cl=getWidth()-cWidth-cParams.rightMargin;
				ct=getHeight()-cHeight-cParams.bottomMargin;
				break;

			}
			
			cr=cl+cWidth;
			cb=ct+cHeight;
			
			
			
			childView.layout(cl, ct, cr, cb);

		}

	}

}
