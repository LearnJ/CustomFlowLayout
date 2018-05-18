package com.example.jxm.listviewsample.bean;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.icu.text.MessagePattern;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jxm.listviewsample.R;

import java.util.ArrayList;

/**
 * Created by jiamao on 2018/5/11.
 */

public class FlowLayout extends ViewGroup {

    private ArrayList<Integer>lineHeights;
    private ArrayList<Integer>viewNumEveryRow;
    private Context mContext;
    private float itemSpace;
    private float lineSpace;


    private boolean needAverage=false;

    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        lineHeights=new ArrayList<>();
        viewNumEveryRow=new ArrayList<>();
        mContext=context;

        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);

        itemSpace=typedArray.getDimension(R.styleable.FlowLayout_itemSpace1,0);
        lineSpace=typedArray.getDimension(R.styleable.FlowLayout_lineSpace1,0);

        typedArray.recycle();

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void layout(){

        int horizotalMargin=0;
        int verticalMargin=0;
        int leftMargin=0;
        int rightMargin=0;
        int childIndex=0;
        int topHeight=0;
        int curr_l=0;
        for (int i=0;i<lineHeights.size();i++){//遍历行
            View child=null;
            childIndex=getIndex(i);
            topHeight=getTopHeight(i);
            if (viewNumEveryRow.get(i)==1){
                child=getChildAt(childIndex);
                if (child==null){
                    return;
                }
                child.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                LayoutParams margin= (LayoutParams) child.getLayoutParams();
                int rowHeight=lineHeights.get(i);
                int w = child.getMeasuredWidth();
                int h = child.getMeasuredHeight();
                int wid=getMeasuredWidth();
                if (w>getMeasuredWidth()){
                    w=getMeasuredWidth();
                }
                leftMargin=(getMeasuredWidth()-w)/2;
                curr_l=0;
                child.layout(leftMargin,topHeight+(rowHeight-h)/2,leftMargin+w,topHeight+h+(rowHeight-h)/2);
                continue;
            }

            int j;
            for (j=0;j<viewNumEveryRow.get(i);j++){//遍历行中view
                child=getChildAt(childIndex+j);
                if (child==null){
                    return;
                }
                LayoutParams margin= (LayoutParams) child.getLayoutParams();
                int marginLeft=margin.leftMargin;
                int marginRight=margin.rightMargin;
                int w = child.getMeasuredWidth();
                int h = child.getMeasuredHeight();
                horizotalMargin+=w;
            }
            int average=(getMeasuredWidth()-horizotalMargin)/(j+1);

            for (j=0;j<viewNumEveryRow.get(i);j++){//遍历行中view
                child=getChildAt(childIndex+j);

                int rowHeight=lineHeights.get(i);
                int w = child.getMeasuredWidth();
                int h = child.getMeasuredHeight();
                child.layout(curr_l+(j+1)*average,topHeight+(rowHeight-h)/2,curr_l+(j+1)*average+w,topHeight+h+(rowHeight-h)/2);
                curr_l+=w;

            }
            horizotalMargin=0;
            curr_l=0;
        }

    }

    /**
     * 获取每行第一个view的索引
     * @param i
     * @return
     */
    private int getIndex(int i){
        int count=0;
        for (int j=0;j<i;j++){
            count+=viewNumEveryRow.get(j);
        }
        return count;
    }

    /**
     * 获取当前行的高度
     * @param i
     * @return
     */
    private int getTopHeight(int i){
        int height=0;
        for (int j=0;j<i;j++){
            height+=lineHeights.get(j);
        }
        return height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);


        int measureWidth= MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight=MeasureSpec.getSize(heightMeasureSpec);


        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int count=getChildCount();

        int maxWidth=0;
        int maxHeight=0;

        int viewNum=0;

        int totalHeight=0;//view的总高度
        int finalWidth=0;//各行宽度的最大值，作为测量值


        int rowWidth=0;//每行的宽度
        for (int i=0;i<count;i++){

            View view=getChildAt(i);
            LayoutParams margin= (LayoutParams) view.getLayoutParams();
            int w=view.getMeasuredWidth()+margin.leftMargin+margin.rightMargin;
            int h=view.getMeasuredHeight()+margin.topMargin+margin.bottomMargin;

            if ((rowWidth+w)>measureWidth){//超出行宽，需要移到下一行时，此时，行高可以决定
                totalHeight+=maxHeight;
                lineHeights.add(maxHeight);
                viewNumEveryRow.add(viewNum);
                finalWidth=Math.max(finalWidth,rowWidth);
                Log.e("jm","maxHeight="+maxHeight);
                if (i==(count-1)) {
                    maxHeight = h;//换行是，最大高度清为0；
                    viewNum=1;
                    finalWidth=Math.max(finalWidth,w);
                    lineHeights.add(maxHeight);
                    viewNumEveryRow.add(viewNum);
                }else{
                    maxHeight=h;
                    rowWidth=w;
                    viewNum=1;
                }
            }else {
                maxHeight=Math.max(maxHeight,h);//记录各行控件的最大高度
                rowWidth+=w;//累加控件的宽度
                viewNum++;
                finalWidth=Math.max(finalWidth,rowWidth);
                if (i==(count-1)) { //结束时，存储结束行的行高
                    lineHeights.add(maxHeight);
                    viewNumEveryRow.add(viewNum);
                }
            }
        }
        totalHeight+=maxHeight;
        finalWidth=Math.min(finalWidth,measureWidth);


        switch (widthMode){
            case MeasureSpec.AT_MOST:

                break;
            case MeasureSpec.EXACTLY:
                finalWidth=measureWidth;
                break;
        }

        switch (heightMode){
            case MeasureSpec.AT_MOST:

                break;
            case MeasureSpec.EXACTLY:
                totalHeight=measureHeight;
                break;
        }

        setMeasuredDimension(finalWidth,totalHeight);
    }

    private int measureHeight(){

        //
//        switch (widthMode){
//            case MeasureSpec.AT_MOST:
//
//                break;
//            case MeasureSpec.EXACTLY:
//
//                break;
//        }
//
//        switch (heightMode){
//            case MeasureSpec.AT_MOST:
//
//                break;
//            case MeasureSpec.EXACTLY:
//
//                break;
//        }


        return 0;


    }

    private int measureWidth(){

        return 0;
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }


    // 继承自margin，支持子视图android:layout_margin属性
    public static class LayoutParams extends MarginLayoutParams {


        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }


        public LayoutParams(int width, int height) {
            super(width, height);
        }


        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }


        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }
    }

int a=100_3___200_00;
    Float b=300_0.0f;
    int lastx=0;
    int lasty=0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                lastx=(int) event.getX();
                lasty= (int) event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                int dy= (int) (event.getY()-lasty);
                if ((getScrollY()<0 && dy>0)||getScrollY()>getTopHeight(lineHeights.size())){
                    break;
                }
                scrollBy(0,-dy);

                break;

            case MotionEvent.ACTION_UP:

                break;



        }
        lasty= (int) event.getY();

        return true;
    }
}
