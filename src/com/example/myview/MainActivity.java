package com.example.myview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	ImagePreviewDialog imagePreviewDialog;

	ImageView iv;
	RelativeLayout re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         re=(RelativeLayout) findViewById(R.id.container);
        iv=(ImageView) findViewById(R.id.iv);
        
        
        
        findViewById(R.id.bar).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imagePreviewDialog = new ImagePreviewDialog(MainActivity.this ,re);
//				iv.setVisibility(View.VISIBLE);
				imagePreviewDialog.setTargetView(iv).show();
				
			}
		});

    }

}
