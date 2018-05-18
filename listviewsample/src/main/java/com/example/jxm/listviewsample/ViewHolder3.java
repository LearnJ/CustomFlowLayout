package com.example.jxm.listviewsample;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jxm.listviewsample.bean.Info3;

/**
 * Created by jiamao on 2018/5/10.
 */

public class ViewHolder3 extends BaseViewHolder {
    private ImageView leftIcon;
    private TextView centerTitle;
    private TextView rightTitle;
    public ViewHolder3(View itemView) {
        super(itemView);
        leftIcon=itemView.findViewById(R.id.recycle_item_left_icon);
        centerTitle=itemView.findViewById(R.id.recycle_item_center_title);
        rightTitle=itemView.findViewById(R.id.recycle_item_right_title);
    }

    @Override
    public void FillView(IData data) {
        super.FillView(data);
        if (data instanceof Info3){
            leftIcon.setImageResource(((Info3) data).getLeft_icon_id());
            centerTitle.setText(((Info3) data).getCenter_str());
            rightTitle.setText(((Info3) data).getRight_str());
        }

    }
}
