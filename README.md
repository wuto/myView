ImagePreviewDialog

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




CircleProgressBar

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
        >
    </com.example.myview.CircleProgressBar>
    
    
    
    ��attr.xml����ӣ�
    <attr name="firstCorlor" format="color"/>
    <attr name="secondCorlor" format="color"/>
    <attr name="circleWidth" format="dimension"/>
    <attr name="dotCount" format="integer"/>
    <attr name="splitSize" format="integer"/>
    
    <declare-styleable name="CircleProgressBar">
        
        <attr name="firstCorlor"/>
        <attr name="secondCorlor"/>
        <attr name="circleWidth"/>
        <attr name="dotCount"/>
        <attr name="splitSize"/>
    </declare-styleable>
    
    
 ��activity��ʵ��OnVolumeChangeListener�ӿڼ������ȵĸı�
    
    
    
voidView 
�������ƣ��·��пհף�

http://blog.csdn.net/lmj623565791/article/details/24529807

ÿ��Բ���ĳ�Բ�ǵģ����·����Ӽ��
    
   1. voidView
   2. 
   �ڲ�����ʹ��
   <com.example.myview.voidView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        custom:firstCorlor="#ff0000"
        custom:secondCorlor="#0000ff"
        custom:circleWidth="20dp"
        custom:dotCount="20"
        custom:splitSize="10"
        custom:spaceCount="6"
        custom:bg="@drawable/ic_launcher"
        android:visibility="gone"
        >
    </com.example.myview.voidView>
   
    	��attr.xml����ӣ�
    <attr name="firstCorlor" format="color"/>
    <attr name="secondCorlor" format="color"/>
    <attr name="circleWidth" format="dimension"/>
    <attr name="dotCount" format="integer"/>
    <attr name="splitSize" format="integer"/>
    <attr name="spaceCount" format="integer"/>
    <attr name="bg" format="reference"/>
    
    <declare-styleable name="CustomVolumControlBar">
        
        <attr name="firstCorlor"/>
        <attr name="secondCorlor"/>
        <attr name="circleWidth"/>
        <attr name="dotCount"/>
        <attr name="splitSize"/>
        <attr name="spaceCount"/>
        <attr name="bg"/>
    </declare-styleable>
    
    
        
 ��activity��ʵ��OnVolumeChangeListener�ӿڼ������ȵĸı�
