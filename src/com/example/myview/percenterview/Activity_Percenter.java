package com.example.myview.percenterview;

import com.example.myview.R;
import com.example.myview.percenterview.PercenterView.OnPercentChangeListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Activity_Percenter extends Activity implements OnPercentChangeListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_percenter);
		 ((PercenterView)findViewById(R.id.percenterview)).setOnPercentChangeListener(this);
		
		
	}
	
	
	@Override
	public void percentChange(int level) {
		Toast.makeText(this, "level:"+level, 0).show();
		
	}
	
}
