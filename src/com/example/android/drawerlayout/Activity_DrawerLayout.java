package com.example.android.drawerlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.android.drawerlayout.MaterialMenuDrawable.Stroke;
import com.example.myview.R;

public class Activity_DrawerLayout extends FragmentActivity {  
	/** DrawerLayout */
	private DrawerLayout mDrawerLayout;
	/** ������˵� */
	private ListView mMenuListView;
	/** �ұ��� */
	private RelativeLayout right_drawer;
	/** �˵��б� */
	private String[] mMenuTitles;
	/** Material Design��� */
	private MaterialMenuIcon mMaterialMenuIcon;
	/** �˵���/�ر�״̬ */
	private boolean isDirection_left = false;
	/** �ұ�����/�ر�״̬ */
	private boolean isDirection_right = false;
	private View showView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawerlayout);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mMenuListView = (ListView) findViewById(R.id.left_drawer);
		right_drawer = (RelativeLayout) findViewById(R.id.right_drawer);
		this.showView = mMenuListView;

		// ��ʼ���˵��б�
		mMenuTitles = getResources().getStringArray(R.array.menu_array);
		mMenuListView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mMenuTitles));
		mMenuListView.setOnItemClickListener(new DrawerItemClickListener());

		// ���ó����ʱ����Ҫ���������Զ�����Ӱ����
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// ����ActionBar�ɼ��������л��˵���������ͼ
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mMaterialMenuIcon = new MaterialMenuIcon(this, Color.WHITE, Stroke.THIN);
		mDrawerLayout.setDrawerListener(new DrawerLayoutStateListener());

		if (savedInstanceState == null) {
			selectItem(0);
		}

	}

	/**
	 * ListView�ϵ�Item����¼�
	 * 
	 */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	/**
	 * DrawerLayout״̬�仯����
	 */
	private class DrawerLayoutStateListener extends
			DrawerLayout.SimpleDrawerListener {
		/**
		 * �������˵�������ʱ��ִ��
		 */
		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {
			showView = drawerView;
			if (drawerView == mMenuListView) {// ����isDirection_left����ִ�ж���
				mMaterialMenuIcon.setTransformationOffset(
						MaterialMenuDrawable.AnimationState.BURGER_ARROW,
						isDirection_left ? 2 - slideOffset : slideOffset);
			} else if (drawerView == right_drawer) {// ����isDirection_right����ִ�ж���
				mMaterialMenuIcon.setTransformationOffset(
						MaterialMenuDrawable.AnimationState.BURGER_ARROW,
						isDirection_right ? 2 - slideOffset : slideOffset);
			}
		}

		/**
		 * �������˵���ʱִ��
		 */
		@Override
		public void onDrawerOpened(android.view.View drawerView) {
			if (drawerView == mMenuListView) {
				isDirection_left = true;
			} else if (drawerView == right_drawer) {
				isDirection_right = true;
			}
		}

		/**
		 * �������˵��ر�ʱִ��
		 */
		@Override
		public void onDrawerClosed(android.view.View drawerView) {
			if (drawerView == mMenuListView) {
				isDirection_left = false;
			} else if (drawerView == right_drawer) {
				isDirection_right = false;
				showView = mMenuListView;
			}
		}
	}

	/**
	 * �л�����ͼ�����Fragment
	 * 
	 * @param position
	 */
	private void selectItem(int position) {
		Fragment fragment = new ContentFragment();
		Bundle args = new Bundle();
		switch (position) {
		case 0:
			args.putString("key", mMenuTitles[position]);
			break;
		case 1:
			args.putString("key", mMenuTitles[position]);
			break;
		case 2:
			args.putString("key", mMenuTitles[position]);
			break;
		case 3:
			args.putString("key", mMenuTitles[position]);
			break;
		default:
			break;
		}
		fragment.setArguments(args); // FragmentActivity������Ĳ˵��б���⴫�ݸ�Fragment
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// ����ѡ����item��title��Ȼ��رղ˵�
		mMenuListView.setItemChecked(position, true);
		setTitle(mMenuTitles[position]);
		mDrawerLayout.closeDrawer(mMenuListView);
	}

	/**
	 * ���ActionBar�ϲ˵�
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			if (showView == mMenuListView) {
				if (!isDirection_left) { // ������˵��ر�ʱ����
					mDrawerLayout.openDrawer(mMenuListView);
				} else {// ������˵���ʱ���ر�
					mDrawerLayout.closeDrawer(mMenuListView);
				}
			} else if (showView == right_drawer) {
				if (!isDirection_right) {// �ұ����ر�ʱ����
					mDrawerLayout.openDrawer(right_drawer);
				} else {// �ұ�����ʱ���ر�
					mDrawerLayout.closeDrawer(right_drawer);
				}
			}
			break;
		case R.id.action_personal:
			if (!isDirection_right) {// �ұ����ر�ʱ����
				if (showView == mMenuListView) {
					mDrawerLayout.closeDrawer(mMenuListView);
				}
				mDrawerLayout.openDrawer(right_drawer);
			} else {// �ұ�����ʱ���ر�
				mDrawerLayout.closeDrawer(right_drawer);
			}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * ����onPostCreate�ص���״̬����ԭ��Ӧ��icon state
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mMaterialMenuIcon.syncState(savedInstanceState);
	}

	/**
	 * ����onSaveInstanceState�ص���״̬�����浱ǰicon state
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		mMaterialMenuIcon.onSaveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}

	/**
	 * ���ز˵�
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
  
}  