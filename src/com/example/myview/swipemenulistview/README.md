 ## SwipeMenuListView
 
 左划右划--删除-打开
 
 https://github.com/baoyongzhang/SwipeMenuListView
 
 https://github.com/daimajia/AndroidSwipeLayout
 
 1. 布局中使用
 
       <com.example.myview.swipemenulistview.SwipeMenuListView
	        android:id="@+id/listView"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent" />
 1.创建一个SwipeMenuCreator
 SwipeMenuCreator creator = new SwipeMenuCreator() {
 		// create "open" item 创建一个item （open）
        SwipeMenuItem openItem = new SwipeMenuItem(
                getApplicationContext());
        // set item background  设置item背景
        openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                0xCE)));
        // set item width  设置item的宽度
        openItem.setWidth(dp2px(90));
        // set item title  设置item的文字
        openItem.setTitle("Open");
        // set item title fontsize  设置item的文字的大小
        openItem.setTitleSize(18);
        // set item title font color  设置item的文字的颜色
        openItem.setTitleColor(Color.WHITE);
        // add to menu    添加item到menu
        menu.addMenuItem(openItem);

        // create "delete" item
        SwipeMenuItem deleteItem = new SwipeMenuItem(
                getApplicationContext());
        // set item background
        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                0x3F, 0x25)));
        // set item width
        deleteItem.setWidth(dp2px(90));
        // set a icon   设置item图标
        deleteItem.setIcon(R.drawable.ic_delete);
        // add to menu
        menu.addMenuItem(deleteItem);
    }
};



// set creator  将creator设置给SwipeMenuListView
mListView.setMenuCreator(creator);

2. 设置menuitem的点击事件  （如：打开，删除）
 

// step 2. listener item click event
		mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(int position,
							SwipeMenu menu, int index) {
						ApplicationInfo item = mAppList.get(position);
						switch (index) {
						case 0:
							// open
							open(item);
							break;
						case 1:
							// delete
							// delete(item);
							mAppList.remove(position);
							mAdapter.notifyDataSetChanged();
							break;
						}
						return false;
					}
				});

 		// set SwipeListener
		mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

			@Override
			public void onSwipeStart(int position) {
				// swipe start
			}

			@Override
			public void onSwipeEnd(int position) {
				// swipe end
			}
		});
		
		
		// set MenuStateChangeListener  menu打开关闭回调
		mListView
				.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
					@Override
					public void onMenuOpen(int position) {
					}

					@Override
					public void onMenuClose(int position) {
					}
				});
 
 		// other setting   
		// listView.setCloseInterpolator(new BounceInterpolator()); //menu关闭的回弹动画
		
		
		// test item long click  menuitem的长按事件
		mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						Toast.makeText(getApplicationContext(),
								position + " long click", Toast.LENGTH_SHORT)
								.show();
						return false;
					}
				});
		
