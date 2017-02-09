
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
    
    
