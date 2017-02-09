package com.example.myviewgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.drawerlayout.Activity_DrawerLayout;
import com.example.myview.R;
import com.example.myview.circleprogressbar.Activity_Circle;
import com.example.myview.imagepreviewdialog.Activity_ImagePreview;
import com.example.myview.percenterview.Activity_Percenter;
import com.example.myview.slidecutlistview.Activity_SlidCutList;
import com.example.myview.swipemenulistview.Activity_Swipemenu;
import com.example.myview.swipemenulistview.SimpleActivity;
import com.example.myviewgroup.Activity_Group;
import com.example.myviewgroup.groupdemo1.Activity_GroupDemo1;
import com.example.myviewgroup.vdhlayout.Activity_VDHLayout;
import com.example.myviewgroup.vdhlayout.Activity_VDHLayout2;

public class Activity_Group extends Activity implements OnItemClickListener {

	private ListView mListView;

	private String mStr[] = { "groupDemi1", "Õœ∂Øview", "Õœ∂Øview¿©’π",
			"DrawerLayout" };
	private Class mCls[] = { Activity_GroupDemo1.class,
			Activity_VDHLayout.class, Activity_VDHLayout2.class,
			Activity_DrawerLayout.class };

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
