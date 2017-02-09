##ImagePreviewDialog

高斯模糊加图片展示（仿ipad qq图片点击展示） 
http://blog.csdn.net/mcy456/article/details/51339149


1. ImagePreviewDialog
2. 然后布局代码：
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
    以及style设置
        <style name="GaussDialog">  
            <item name="android:windowBackground">@color/transparent</item>  
            <item name="android:windowNoTitle">true</item>  
            <item name="android:windowFullscreen">false</item>  
            <item name="android:windowIsTranslucent">true</item>  
            <item name="android:backgroundDimEnabled">false</item>  
            <item name="android:background">#00000000</item>  
        </style>  
        色值设置：
        <color name="transparent_gray">#88000000</color>
        使用方法很简单，只要两行代码：
    
	imagePreviewDialog = new ImagePreviewDialog(this);
	[imagePreviewDialog = new ImagePreviewDialog(this , view);]
			view传入整个页面的布局
	
	imagePreviewDialog.setTargetView(iv).show();
			iv传入要显示的imageView
	
	(第二行代码，需要在目标View显示后进行调用，否则获得的getmeasuredwidth以及高度会为0）

1. gaussBg = convertViewToBitmap(mParentView,mParentView.getWidth(),mParentView.getHeight()); 报空指针:


ImagePreviewDialog构造方法中将mParentView赋值代码注释，自己写方法传入对应的View（比如在activity中对应的就是R.layout.activity_XX）;
调用的第一行代码，放在setContentView之后


2. IllegalArgumentException异常

获取的高和宽为零，传入的TargetView应该是已经显示的View




##CircleProgressBar

自定义圆形progressbar 加 音量控制自定义view 

http://blog.csdn.net/qq_28872867/article/details/51915989

1. CircleProgressBar
2. 
在布局中使用
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
    
    
    
    在attr.xml中添加：
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
    
    
 在activity中实现OnVolumeChangeListener接口监听进度的改变
    
    
    
##PercenterView 
音量控制（下方有空白）

http://blog.csdn.net/lmj623565791/article/details/24529807

每段圆弧改成圆角的，在下方增加间距
    
   1. PercenterView
   2. 
   在布局中使用
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
   
    在attr.xml中添加：
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
    
    
        
 在activity中实现OnVolumeChangeListener接口监听进度的改变
 
 
 
 
 ## SwipeMenuListView
 
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
		
