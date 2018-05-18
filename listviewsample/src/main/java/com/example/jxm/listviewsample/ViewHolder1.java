package com.example.jxm.listviewsample;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jxm.listviewsample.bean.Info1;

/**
 * Created by jiamao on 2018/5/10.
 */

public class ViewHolder1 extends BaseViewHolder {

    private ImageView leftIcon;
    private TextView centerTitle;
    private TextView rightTitle;
    public ViewHolder1(View itemView) {
        super(itemView);
        leftIcon=itemView.findViewById(R.id.recycle_item_left_icon);
        centerTitle=itemView.findViewById(R.id.recycle_item_center_title);
        rightTitle=itemView.findViewById(R.id.recycle_item_right_title);
    }

    @Override
    public void FillView(IData data) {
        super.FillView(data);
        if (data instanceof Info1){
            leftIcon.setImageResource(((Info1) data).getLeft_icon_id());
            centerTitle.setText(((Info1) data).getCenter_str());
            rightTitle.setText(((Info1) data).getRight_str());
        }
    }
}
