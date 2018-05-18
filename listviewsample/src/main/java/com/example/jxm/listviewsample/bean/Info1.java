package com.example.jxm.listviewsample.bean;

import com.example.jxm.listviewsample.IData;

/**
 * Created by jiamao on 2018/5/10.
 */

public class Info1 implements IData {
    @Override
    public int typ() {
        return RecycleItemTypeConstant.RECYCLEVIEW_ITEM_TYPE_1;
    }

    private int left_icon_id;
    private String center_str;
    private String right_str;

    public int getLeft_icon_id() {
        return left_icon_id;
    }

    public void setLeft_icon_id(int left_icon_id) {
        this.left_icon_id = left_icon_id;
    }

    public String getCenter_str() {
        return center_str;
    }

    public void setCenter_str(String center_str) {
        this.center_str = center_str;
    }

    public String getRight_str() {
        return right_str;
    }

    public void setRight_str(String right_str) {
        this.right_str = right_str;
    }
}
