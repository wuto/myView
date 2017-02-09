package com.example.myview.circleprogressbar;

import android.app.Activity;
import android.os.Bundle;

import com.example.myview.R;
import com.example.myview.circleprogressbar.CircleProgressBar.OnVolumeChangeListener;

public class Activity_Circle extends Activity implements OnVolumeChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle);
		((CircleProgressBar) findViewById(R.id.circleprogressbar))
				.setOnVolumeChangeListener(this);

	}

	@Override
	public void volumeChange(int level) {
		// TODO Auto-generated method stub

	}

}
