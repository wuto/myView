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

