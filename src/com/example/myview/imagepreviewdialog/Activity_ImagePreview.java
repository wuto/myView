package com.example.myview.imagepreviewdialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myview.R;

public class Activity_ImagePreview extends Activity {

	private ImagePreviewDialog mImagePreviewDialog;
	private ImageView iv;
	private RelativeLayout re_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageprevie);

		iv = (ImageView) findViewById(R.id.iv);
		re_layout = (RelativeLayout) findViewById(R.id.re_layout);

		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mImagePreviewDialog = new ImagePreviewDialog(
						Activity_ImagePreview.this, re_layout);
				mImagePreviewDialog.setTargetView(iv).show();
				
				WindowManager wm = getWindowManager();
				Display display = wm.getDefaultDisplay();
				android.view.WindowManager.LayoutParams lp = mImagePreviewDialog.getWindow().getAttributes();   
				int a=lp.width;
				int b=lp.height;

			}
		});

	}
}
