 ## SwipeMenuListView
 
 ���һ�--ɾ��-��
 
 https://github.com/baoyongzhang/SwipeMenuListView
 
 https://github.com/daimajia/AndroidSwipeLayout
 
 1. ������ʹ��
 
       <com.example.myview.swipemenulistview.SwipeMenuListView
	        android:id="@+id/listView"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent" />
 1.����һ��SwipeMenuCreator
 SwipeMenuCreator creator = new SwipeMenuCreator() {
 		// create "open" item ����һ��item ��open��
        SwipeMenuItem openItem = new SwipeMenuItem(
                getApplicationContext());
        // set item background  ����item����
        openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                0xCE)));
        // set item width  ����item�Ŀ��
        openItem.setWidth(dp2px(90));
        // set item title  ����item������
        openItem.setTitle("Open");
        // set item title fontsize  ����item�����ֵĴ�С
        openItem.setTitleSize(18);
        // set item title font color  ����item�����ֵ���ɫ
        openItem.setTitleColor(Color.WHITE);
        // add to menu    ���item��menu
        menu.addMenuItem(openItem);

        // create "delete" item
        SwipeMenuItem deleteItem = new SwipeMenuItem(
                getApplicationContext());
        // set item background
        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                0x3F, 0x25)));
        // set item width
        deleteItem.setWidth(dp2px(90));
        // set a icon   ����itemͼ��
        deleteItem.setIcon(R.drawable.ic_delete);
        // add to menu
        menu.addMenuItem(deleteItem);
    }
};



// set creator  ��creator���ø�SwipeMenuListView
mListView.setMenuCreator(creator);

2. ����menuitem�ĵ���¼�  ���磺�򿪣�ɾ����
 

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
		
		
		// set MenuStateChangeListener  menu�򿪹رջص�
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
		// listView.setCloseInterpolator(new BounceInterpolator()); //menu�رյĻص�����
		
		
		// test item long click  menuitem�ĳ����¼�
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
		
