##ImagePreviewDialog

��˹ģ����ͼƬչʾ����ipad qqͼƬ���չʾ�� 
http://blog.csdn.net/mcy456/article/details/51339149


1. ImagePreviewDialog
2. Ȼ�󲼾ִ��룺
  dialog_gauss_bg_pic.xml:
    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        >  
        <ImageView  
            android:id="@+id/iv_gauss"  
            android:layout_width="match_parent"  
            android:layout_height="match_parent"/>  
        <ImageView  
            android:id="@+id/iv_shadow"  
            android:layout_width="match_parent"  
            android:layout_height="match_parent"  
            android:background="@color/transparent_gray"/>  
      
    </FrameLayout>  
    �Լ�style����
        <style name="GaussDialog">  
            <item name="android:windowBackground">@color/transparent</item>  
            <item name="android:windowNoTitle">true</item>  
            <item name="android:windowFullscreen">false</item>  
            <item name="android:windowIsTranslucent">true</item>  
            <item name="android:backgroundDimEnabled">false</item>  
            <item name="android:background">#00000000</item>  
        </style>  
        ɫֵ���ã�
        <color name="transparent_gray">#88000000</color>
        ʹ�÷����ܼ򵥣�ֻҪ���д��룺
    
	imagePreviewDialog = new ImagePreviewDialog(this);
	[imagePreviewDialog = new ImagePreviewDialog(this , view);]
			view��������ҳ��Ĳ���
	
	imagePreviewDialog.setTargetView(iv).show();
			iv����Ҫ��ʾ��imageView
	
	(�ڶ��д��룬��Ҫ��Ŀ��View��ʾ����е��ã������õ�getmeasuredwidth�Լ��߶Ȼ�Ϊ0��

1. gaussBg = convertViewToBitmap(mParentView,mParentView.getWidth(),mParentView.getHeight()); ����ָ��:


ImagePreviewDialog���췽���н�mParentView��ֵ����ע�ͣ��Լ�д���������Ӧ��View��������activity�ж�Ӧ�ľ���R.layout.activity_XX��;
���õĵ�һ�д��룬����setContentView֮��


2. IllegalArgumentException�쳣

��ȡ�ĸߺͿ�Ϊ�㣬�����TargetViewӦ�����Ѿ���ʾ��View




##CircleProgressBar

�Զ���Բ��progressbar �� ���������Զ���view 

http://blog.csdn.net/qq_28872867/article/details/51915989

1. CircleProgressBar
2. 
�ڲ�����ʹ��
<com.example.myview.CircleProgressBar
         android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        custom:firstCorlor="#ff0000"
        custom:secondCorlor="#0000ff"
        custom:circleWidth="10dp"
        custom:dotCount="100"
        custom:splitSize="1"
        custom:continuity="false"
        >
    </com.example.myview.CircleProgressBar>
    
    
    
    ��attr.xml����ӣ�
    <attr name="firstCorlor" format="color"/>
    <attr name="secondCorlor" format="color"/>
    <attr name="circleWidth" format="dimension"/>
    <attr name="dotCount" format="integer"/>
    <attr name="splitSize" format="integer"/>
    <attr name="continuity" format="boolean"/>
    
    <declare-styleable name="CircleProgressBar">
        
        <attr name="firstCorlor"/>
        <attr name="secondCorlor"/>
        <attr name="circleWidth"/>
        <attr name="dotCount"/>
        <attr name="splitSize"/>
        <attr name="continuity"/>
    </declare-styleable>
    
    
 ��activity��ʵ��OnVolumeChangeListener�ӿڼ������ȵĸı�
    
    
    
##PercenterView 
�������ƣ��·��пհף�

http://blog.csdn.net/lmj623565791/article/details/24529807

ÿ��Բ���ĳ�Բ�ǵģ����·����Ӽ��
    
   1. PercenterView
   2. 
   �ڲ�����ʹ��
   <com.example.myview.PercenterView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        custom:firstCorlor="#ff0000"
        custom:secondCorlor="#0000ff"
        custom:circleWidth="20dp"
        custom:dotCount="20"
        custom:splitSize="10"
        custom:spaceCount="6"
        custom:bg="@drawable/ic_launcher"
       custom:continuity="false"
        >
    </com.example.myview.PercenterView>
   
    ��attr.xml����ӣ�
    <attr name="firstCorlor" format="color"/>
    <attr name="secondCorlor" format="color"/>
    <attr name="circleWidth" format="dimension"/>
    <attr name="dotCount" format="integer"/>
    <attr name="splitSize" format="integer"/>
    <attr name="spaceCount" format="integer"/>
    <attr name="bg" format="reference"/>
    <attr name="continuity" format="boolean"/>
    
    <declare-styleable name="CustomVolumControlBar">
        
        <attr name="firstCorlor"/>
        <attr name="secondCorlor"/>
        <attr name="circleWidth"/>
        <attr name="dotCount"/>
        <attr name="splitSize"/>
        <attr name="spaceCount"/>
        <attr name="bg"/>
        <attr name="continuity"/>
    </declare-styleable>
    
    
        
 ��activity��ʵ��OnVolumeChangeListener�ӿڼ������ȵĸı�
 
 
 
 
 ## SwipeMenuListView
 
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
		
