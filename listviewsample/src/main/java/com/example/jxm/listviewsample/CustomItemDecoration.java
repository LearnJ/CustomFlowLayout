package com.example.jxm.listviewsample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jiamao on 2018/5/10.
 */

public class CustomItemDecoration extends RecyclerView.ItemDecoration {


    Paint mPaint;
    public CustomItemDecoration() {
        super();
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

    }

    /**
     *在itemView绘制之前绘制，所以要通过getItemOffsets设置好距离，留出空间，防止被后绘得的itemView覆盖掉
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        int childCount = parent.getChildCount();
//        for ( int i = 0; i < childCount; i++ ) {
//            View view = parent.getChildAt(i);
//            int index = parent.getChildAdapterPosition(view);
//            float top = view.getBottom();
//            float extra=view.getHeight()/2;
//            c.drawCircle(50, top+extra,20,mPaint);
//        }
    }

    /**
     * 在itemVie上面绘制，根据设计，使用canvas进行绘制即可
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount = parent.getChildCount();
        for ( int i = 0; i < childCount; i++ ) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
            float top = view.getTop();
            float extra=view.getHeight()/2;
            c.drawCircle(50, top+extra,20,mPaint);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view)!=0) {
            outRect.top = 10;
            outRect.left=100;
        }
    }
}
