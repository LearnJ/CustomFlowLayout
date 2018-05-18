package com.example.jxm.listviewsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jxm.listviewsample.bean.RecycleItemTypeConstant;

import java.util.List;

/**
 * Created by jiamao on 2018/5/10.
 */

public class RecycleAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<IData>listDatas;
    private Context mContext;

    public RecycleAdapter(List<IData> listDatas, Context mContext) {
        this.listDatas = listDatas;
        this.mContext = mContext;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        BaseViewHolder viewHolder=null;
        switch (viewType){

            case RecycleItemTypeConstant.RECYCLEVIEW_ITEM_TYPE_1:
                viewHolder=onCreateViewHolder1(parent);
                break;
            case RecycleItemTypeConstant.RECYCLEVIEW_ITEM_TYPE_2:
                viewHolder=onCreateViewHolder2(parent);
                break;
            case RecycleItemTypeConstant.RECYCLEVIEW_ITEM_TYPE_3:
                viewHolder=onCreateViewHolder3(parent);
                break;
            default:
                viewHolder=onCreateDefaultViewHolder(parent);

                    break;

        }

        return viewHolder;
    }

    private BaseViewHolder onCreateDefaultViewHolder(ViewGroup parent){
        TextView textView = new TextView(mContext);
        BaseViewHolder holder = new BaseViewHolder(textView);
        return holder;
    }

    private BaseViewHolder onCreateViewHolder1(ViewGroup parent){
        View itemView= LayoutInflater.from(mContext).inflate(R.layout.layout_type_1,parent,false);
        ViewHolder1 viewHolder=new ViewHolder1(itemView);
        return viewHolder;
    }

    private BaseViewHolder onCreateViewHolder2(ViewGroup parent){
        View itemView= LayoutInflater.from(mContext).inflate(R.layout.layout_type_2,parent,false);
        ViewHolder2 viewHolder=new ViewHolder2(itemView);
        return viewHolder;
    }

    private BaseViewHolder onCreateViewHolder3(ViewGroup parent){
        View itemView= LayoutInflater.from(mContext).inflate(R.layout.layout_type_3,parent,false);
        ViewHolder3 viewHolder=new ViewHolder3(itemView);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        try {
            holder.FillView(listDatas.get(position));
        }catch (Exception e){
            Log.i("RecycleView","onLayout Error: " + e.toString() + "for holder: "
                    + holder.getClass().getSimpleName());
        }
    }

    @Override
    public int getItemCount() {
        return listDatas.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position<0||position>listDatas.size()-1){
            return -1;
        }
        return listDatas.get(position).typ();
    }
}
