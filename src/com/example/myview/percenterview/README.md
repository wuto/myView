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
 
 
 