ImagePreviewDialog

高斯模糊加图片展示（仿ipad qq图片点击展示） 
http://blog.csdn.net/mcy456/article/details/51339149

1. gaussBg = convertViewToBitmap(mParentView,mParentView.getWidth(),mParentView.getHeight()); 报空指针:


ImagePreviewDialog构造方法中将mParentView赋值代码注释，自己写方法传入对应的View（比如在activity中对应的就是R.layout.activity_XX）;
调用的第一行代码，放在setContentView之后


2. IllegalArgumentException异常

获取的高和宽为零，传入的TargetView应该是已经显示的View