package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myview.Activity_View;
import com.example.myview.R;
import com.example.myview.circleprogressbar.Activity_Circle;
import com.example.myview.imagepreviewdialog.Activity_ImagePreview;
import com.example.myview.percenterview.Activity_Percenter;
import com.example.myview.slidecutlistview.Activity_SlidCutList;
import com.example.myview.swipemenulistview.Activity_Swipemenu;
import com.example.myview.swipemenulistview.SimpleActivity;
import com.example.myviewgroup.Activity_Group;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView mListView;

	private String mStr[] = { "自定义View", "自定义ViewGroup" };
	private Class mCls[] = { Activity_View.class, Activity_Group.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.listview);
		mListView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mStr));
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int poision, long id) {
		if (mCls.length > poision && mCls[poision] != null) {
			Intent intent = new Intent(this, mCls[poision]);
			startActivity(intent);
		}

	}

}
