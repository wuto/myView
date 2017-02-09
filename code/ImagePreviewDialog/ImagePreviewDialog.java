package com.example.myview.imagepreviewdialog;
import com.example.myview.R;
import com.example.myview.R.id;
import com.example.myview.R.layout;
import com.example.myview.R.style;

import android.animation.ObjectAnimator;  
import android.animation.ValueAnimator;  
import android.app.Activity;  
import android.app.Dialog;  
import android.content.Context;  
import android.graphics.Bitmap;  
import android.graphics.Canvas;  
import android.graphics.Matrix;  
import android.graphics.Rect;  
import android.util.Log;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.view.animation.AlphaAnimation;  
import android.view.animation.Animation;  
import android.widget.FrameLayout;  
import android.widget.ImageView;  
import android.widget.LinearLayout;  



/** 
 * Created by AngusFine on 2016/3/22. 
 * 高斯模糊背景
 */  
public class ImagePreviewDialog extends Dialog implements View.OnClickListener{  
    private final int animationTime = 250; //动画时间  
    private Context context;  
    private ImageView targetView; //目标ImageView  
    private View mParentView; //DisplayFrame氛围内的RootView  
    private int[]location = new int[2]; //targetView 屏幕坐标  
    private Bitmap gaussBg; //mParentView高斯的结果  
    private ImageView ivGauss;  
    private ImageView ivShadow;  
    private ImageView ivShow; //targetView展示  
    private View currentView; //dialog对应的View  
    private int currentW;  
    private int currentH;  
    private int displayH;  
    private Rect rect; //displayframe对应的存储信息  
    private boolean vertical = false;  
    private boolean backable = true;  
    float fY; //动画终点Y坐标  
    float dY;  
    float fX;  
    float dX;  

    /** 
     * 
     * @param context 
     * 类中方法调用对应View的绘制顺序，不可修改 
     */  
    public ImagePreviewDialog(Context context , View view){  
        super(context, R.style.GaussDialog);  
        this.context = context;  
        
        this.mParentView=view;
//        Log.i("DecordhashCode",String.valueOf(((Activity)context).getWindow().getDecorView().hashCode()));  
//        ViewGroup decor = (ViewGroup) ((Activity)context).getWindow().getDecorView();  
//        Log.i("DecordhashCoded",String.valueOf(decor.hashCode()));  
//        for(int i = 0;i<decor.getChildCount();i++){  
//            if(decor.getChildAt(i)instanceof LinearLayout){  
//                ViewGroup ll = (ViewGroup)decor.getChildAt(i);  
//                for(int j = 0;j<ll.getChildCount();j++){  
//                    if (ll.getChildAt(j)instanceof FrameLayout){  
//                        this.mParentView = ll.getChildAt(j);  
//                        break;  
//                    }  
//                }  
//            }  
//        }  
        initBg();  
        getStatusBarInfo();  
    }  

    
    public ImagePreviewDialog setTargetView(ImageView view){  
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);  
        targetView = view;  
        getImageViewInfo();  
        initTargetView();  
        initGaussBitmap();  
        initAnimation();  
        return this;  
    }  

    /** 
     * bg初始化 
     */  
    private void initBg(){  
        currentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_gauss_bg_pic,null);  
        setContentView(currentView);  
        ivGauss = (ImageView) currentView.findViewById(R.id.iv_gauss);  
        ivShadow = (ImageView) currentView.findViewById(R.id.iv_shadow);  
        currentView.setOnClickListener(this);  
    }  

    /** 
     * 获取展示View的对应信息 
     */  
    private void getImageViewInfo(){  
        targetView.getLocationOnScreen(location);  
        currentW = targetView.getMeasuredWidth();  
        currentH = targetView.getMeasuredHeight();  
    }  


    /** 
     * 状态栏信息 
     */  
    private void getStatusBarInfo(){  
        rect = new Rect();  
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);  
        displayH = ((Activity)context).getWindow().getDecorView().getMeasuredHeight();  
    }  

    /** 
     * 高斯模糊 
     */  
    private void initGaussBitmap(){  
        gaussBg = convertViewToBitmap(mParentView,mParentView.getWidth(),mParentView.getHeight());  
        gaussBg = zoomImage(gaussBg,mParentView.getMeasuredWidth()/16,mParentView.getMeasuredHeight()/16);  
        gaussBg = gaussBlur(gaussBg,5,true);  
        ivGauss.setImageBitmap(gaussBg);  
    }  


    /** 
     * 加载展示View 
     */  
    private void initTargetView(){  
        FrameLayout.LayoutParams layoutParams= new FrameLayout.LayoutParams(currentW,currentH);  
        ivShow = new ImageView(context);  
        ivShow.setImageDrawable(targetView.getDrawable());  
        ivShow.setX(location[0]);  
        ivShow.setY(location[1]-rect.top);  
        ivShow.setScaleType(targetView.getScaleType());  
        ivShow.setLayoutParams(layoutParams);  
        ((ViewGroup)currentView).addView(ivShow);  
    }  

    private void initAnimation(){  
        float w = targetView.getMeasuredWidth();  
        float h = targetView.getMeasuredHeight();  
        float dw = rect.right - rect.left;  
        final float dh = rect.bottom - rect.top;  
        float ratio = h/w;  
        float dRatio = dh/dw;  
        final float maxRatio;  
        backable = true;  
        ivShadow.setAlpha(0f);  
        if(ratio>dRatio){  
            vertical = true;  
            maxRatio = dh/h;  
            fY = ((maxRatio-1)*h)/2;  
            dY = location[1]-fY;  
            fX = location[0]-dw/2+w/2;  
        }else{  
            maxRatio = dw/w;  
            dY = location[1] - dh/2 +h/2;  
            fX = ((maxRatio-1)*w)/2;  
            dX = location[0]-fX;  
        }  
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);  
        alphaAnimation.setDuration(animationTime);  
        alphaAnimation.setFillAfter(true);  
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivShow,"an",1.0f,maxRatio).setDuration(200);  
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  
            @Override  
            public void onAnimationUpdate(ValueAnimator animation) {  
                float cVal = (Float) animation.getAnimatedValue();  
                float YDRatio = (cVal-1)/(maxRatio-1);  
                float dRatio = 1-YDRatio;  
                if(vertical) {  
                    ivShow.setScaleX(cVal);  
                    ivShow.setScaleY(cVal);  
                    ivShow.setX(location[0] - fX * (YDRatio));  
                    ivShow.setY(dY * dRatio + fY-rect.top*dRatio);  
                }else{  
                    ivShow.setY(location[1]-rect.top*dRatio-dY*YDRatio);  
                    ivShow.setX(dX*dRatio+fX);  
                    ivShow.setScaleX(cVal);  
                    ivShow.setScaleY(cVal);  
                }  
                ivShadow.setAlpha(YDRatio);  
            }  
        });  
        ivGauss.startAnimation(alphaAnimation);  
        animator.start();  

    }  

    private void backAnimation(){  
        if(!backable)  
            return;  
        backable = false;  
        float w = targetView.getMeasuredWidth();  
        float h = targetView.getMeasuredHeight();  
        float dw = rect.right - rect.left;  
        final float dh = rect.bottom - rect.top;  
        float ratio = h/w;  
        float dRatio = dh/dw;  
        final float maxRatio;  
        if(ratio>dRatio){  
            vertical = true;  
            maxRatio = dh/h;  
        }else{  
            maxRatio = dw/w;  
            fX = ((maxRatio-1)*w)/2;  
            dX = location[0] - fX;  
        }  
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0f);  
        alphaAnimation.setDuration(animationTime);  
        alphaAnimation.setFillAfter(true);  
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {  
            @Override  
            public void onAnimationStart(Animation animation) {  

            }  

            @Override  
            public void onAnimationEnd(Animation animation) {  
                ivShow.setVisibility(View.GONE);  
                ImagePreviewDialog.super.dismiss();  
            }  

            @Override  
            public void onAnimationRepeat(Animation animation) {  

            }  
        });  

        ObjectAnimator animator = ObjectAnimator.ofFloat(ivShow,"an",maxRatio,1f).setDuration(animationTime);  
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  
            @Override  
            public void onAnimationUpdate(ValueAnimator animation) {  
                float cVal = (Float) animation.getAnimatedValue();  
                float YDRatio = (cVal-1)/(maxRatio-1);  
                float dRatio = 1-YDRatio;  
                if(vertical) {  
                    ivShow.setScaleX(cVal);  
                    ivShow.setScaleY(cVal);  
                    ivShow.setX(location[0] - fX * (YDRatio));  
                    ivShow.setY(dY * dRatio + fY-rect.top*dRatio);  
                }else{  
                    ivShow.setY(location[1]-rect.top*dRatio-dY*YDRatio);  
                    ivShow.setX(fX+dRatio*dX);  
                    ivShow.setScaleX(cVal);  
                    ivShow.setScaleY(cVal);  
                }  
                ivShow.setAlpha(YDRatio);  
            }  
        });  
        animator.start();  

        ivGauss.startAnimation(alphaAnimation);  
        ivShadow.startAnimation(alphaAnimation);  

    }  

    @Override  
    public void onBackPressed() {  
        backAnimation();  
    }  

    /** 
     * 图片缩放 
     * @param targetBitmap 目标bitmap 
     * @param newWidth  目标宽度 
     * @param newHeight  目标高度 
     * @return 
     */  
    private Bitmap zoomImage(Bitmap targetBitmap, double newWidth, double newHeight) {  
        float width = targetBitmap.getWidth();  
        float height = targetBitmap.getHeight();  
        Matrix matrix = new Matrix();  
        float scaleWidth = ((float) newWidth) / width;  
        float scaleHeight = ((float) newHeight) / height;  
        matrix.postScale(scaleWidth, scaleHeight);  
        Bitmap bitmap = Bitmap.createBitmap(targetBitmap, 0, 0, (int) width, (int) height, matrix, true);  
        return bitmap;  
    }  

    /** 
     * View转Bitmap 
     * @param view 目标View 
     * @param bitmapWidth 宽度 
     * @param bitmapHeight 高度 
     * @return 
     */  
    private Bitmap convertViewToBitmap(View view, int bitmapWidth, int bitmapHeight){  
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);  
        view.draw(new Canvas(bitmap));  
        return bitmap;  
    }  

    /** 
     * 高斯模糊 
     * @param sentBitmap 目标bitmap 
     * @param radius 高斯半径 
     * @param canReuseInBitmap  是否原图修改 
     * @return 
     */  
    private Bitmap gaussBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {  
        Bitmap bitmap;  
        if (canReuseInBitmap) {  
            bitmap = sentBitmap;  
        } else {  
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);  
        }  

        if (radius < 1) {  
            return (null);  
        }  

        int w = bitmap.getWidth();  
        int h = bitmap.getHeight();  

        int[] pix = new int[w * h];  
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);  

        int wm = w - 1;  
        int hm = h - 1;  
        int wh = w * h;  
        int div = radius + radius + 1;  

        int r[] = new int[wh];  
        int g[] = new int[wh];  
        int b[] = new int[wh];  
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;  
        int vmin[] = new int[Math.max(w, h)];  

        int divsum = (div + 1) >> 1;  
        divsum *= divsum;  
        int dv[] = new int[256 * divsum];  
        for (i = 0; i < 256 * divsum; i++) {  
            dv[i] = (i / divsum);  
        }  

        yw = yi = 0;  

        int[][] stack = new int[div][3];  
        int stackpointer;  
        int stackstart;  
        int[] sir;  
        int rbs;  
        int r1 = radius + 1;  
        int routsum, goutsum, boutsum;  
        int rinsum, ginsum, binsum;  

        for (y = 0; y < h; y++) {  
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;  
            for (i = -radius; i <= radius; i++) {  
                p = pix[yi + Math.min(wm, Math.max(i, 0))];  
                sir = stack[i + radius];  
                sir[0] = (p & 0xff0000) >> 16;  
                sir[1] = (p & 0x00ff00) >> 8;  
                sir[2] = (p & 0x0000ff);  
                rbs = r1 - Math.abs(i);  
                rsum += sir[0] * rbs;  
                gsum += sir[1] * rbs;  
                bsum += sir[2] * rbs;  
                if (i > 0) {  
                    rinsum += sir[0];  
                    ginsum += sir[1];  
                    binsum += sir[2];  
                } else {  
                    routsum += sir[0];  
                    goutsum += sir[1];  
                    boutsum += sir[2];  
                }  
            }  
            stackpointer = radius;  

            for (x = 0; x < w; x++) {  

                r[yi] = dv[rsum];  
                g[yi] = dv[gsum];  
                b[yi] = dv[bsum];  

                rsum -= routsum;  
                gsum -= goutsum;  
                bsum -= boutsum;  

                stackstart = stackpointer - radius + div;  
                sir = stack[stackstart % div];  

                routsum -= sir[0];  
                goutsum -= sir[1];  
                boutsum -= sir[2];  

                if (y == 0) {  
                    vmin[x] = Math.min(x + radius + 1, wm);  
                }  
                p = pix[yw + vmin[x]];  

                sir[0] = (p & 0xff0000) >> 16;  
                sir[1] = (p & 0x00ff00) >> 8;  
                sir[2] = (p & 0x0000ff);  

                rinsum += sir[0];  
                ginsum += sir[1];  
                binsum += sir[2];  

                rsum += rinsum;  
                gsum += ginsum;  
                bsum += binsum;  

                stackpointer = (stackpointer + 1) % div;  
                sir = stack[(stackpointer) % div];  

                routsum += sir[0];  
                goutsum += sir[1];  
                boutsum += sir[2];  

                rinsum -= sir[0];  
                ginsum -= sir[1];  
                binsum -= sir[2];  

                yi++;  
            }  
            yw += w;  
        }  
        for (x = 0; x < w; x++) {  
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;  
            yp = -radius * w;  
            for (i = -radius; i <= radius; i++) {  
                yi = Math.max(0, yp) + x;  

                sir = stack[i + radius];  

                sir[0] = r[yi];  
                sir[1] = g[yi];  
                sir[2] = b[yi];  

                rbs = r1 - Math.abs(i);  

                rsum += r[yi] * rbs;  
                gsum += g[yi] * rbs;  
                bsum += b[yi] * rbs;  

                if (i > 0) {  
                    rinsum += sir[0];  
                    ginsum += sir[1];  
                    binsum += sir[2];  
                } else {  
                    routsum += sir[0];  
                    goutsum += sir[1];  
                    boutsum += sir[2];  
                }  

                if (i < hm) {  
                    yp += w;  
                }  
            }  
            yi = x;  
            stackpointer = radius;  
            for (y = 0; y < h; y++) {  
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];  
                rsum -= routsum;  
                gsum -= goutsum;  
                bsum -= boutsum;  
                stackstart = stackpointer - radius + div;  
                sir = stack[stackstart % div];  
                routsum -= sir[0];  
                goutsum -= sir[1];  
                boutsum -= sir[2];  

                if (x == 0) {  
                    vmin[y] = Math.min(y + r1, hm) * w;  
                }  
                p = x + vmin[y];  

                sir[0] = r[p];  
                sir[1] = g[p];  
                sir[2] = b[p];  

                rinsum += sir[0];  
                ginsum += sir[1];  
                binsum += sir[2];  

                rsum += rinsum;  
                gsum += ginsum;  
                bsum += binsum;  

                stackpointer = (stackpointer + 1) % div;  
                sir = stack[stackpointer];  

                routsum += sir[0];  
                goutsum += sir[1];  
                boutsum += sir[2];  

                rinsum -= sir[0];  
                ginsum -= sir[1];  
                binsum -= sir[2];  

                yi += w;  
            }  
        }  
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);  
        return (bitmap);  
    }  

    @Override  
    public void onClick(View v) {  
        backAnimation();   
    }  
}  