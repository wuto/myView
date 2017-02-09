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
		 * ��ô�ViewGroup�ϼ�����Ϊ���Ƽ��Ŀ�͸ߣ��Լ�����ģʽ
		 */
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		// ��������е�childView�Ŀ�͸�
		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int cCount = getChildCount();

		/**
		 * ��¼�����wrap_content�����õĿ�͸�
		 */
		int width = 0;
		int height = 0;

		int cWidth = 0;
		int cHeight = 0;
		MarginLayoutParams cParams = null;

		// ���ڼ����������childView�ĸ߶�
		int lHeight = 0;
		// ���ڼ����ұ�����childView�ĸ߶ȣ����ո߶�ȡ����֮���ֵ
		int rHeight = 0;

		// ���ڼ����ϱ�����childView�Ŀ��
		int tWidth = 0;
		// ���ڼ�����������childiew�Ŀ�ȣ����տ��ȡ����֮���ֵ
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
			 * �����wrap_content����Ϊ���Ǽ����ֵ ����ֱ������Ϊ�����������ֵ
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
		 * ��������childView�������͸ߣ��Լ�margin���в���
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
