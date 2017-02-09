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
 
 
 