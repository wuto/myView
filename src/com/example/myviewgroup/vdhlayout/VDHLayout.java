package com.example.myviewgroup.vdhlayout;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class VDHLayout extends LinearLayout {
	
	ViewDragHelper mDragger;

	public VDHLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		mDragger=ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback(){
            @Override
            public boolean tryCaptureView(View child, int pointerId)
            {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx)
            {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy)
            {
                return top;
            }
            
		});
		
		
	}

	public VDHLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public VDHLayout(Context context) {
		this(context, null);
	}
	
	
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		return mDragger.shouldInterceptTouchEvent(event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mDragger.processTouchEvent(event);
		return true;
	}
	

}
