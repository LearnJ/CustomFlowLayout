package com.example.jxm.listviewsample;

import android.view.View;
import android.widget.TextView;

import com.example.jxm.listviewsample.bean.Info2;

/**
 * Created by jiamao on 2018/5/10.
 */

public class ViewHolder2 extends BaseViewHolder {
    private TextView title;
    public ViewHolder2(View itemView) {
        super(itemView);

        title=itemView.findViewById(R.id.recycle_item_center_title);

    }

    @Override
    public void FillView(IData data) {
        super.FillView(data);
        title.setText(((Info2)data).getTitle());
    }
}
