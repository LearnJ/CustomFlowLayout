package com.example.jxm.listviewsample.bean;

import com.example.jxm.listviewsample.IData;

/**
 * Created by jiamao on 2018/5/10.
 */

public class Info2 implements IData {
    @Override
    public int typ() {
        return RecycleItemTypeConstant.RECYCLEVIEW_ITEM_TYPE_2;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
