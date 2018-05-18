package com.example.jxm.listviewsample;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jxm.listviewsample.bean.FlowLayout;
import com.example.jxm.listviewsample.bean.Info1;
import com.example.jxm.listviewsample.bean.Info2;
import com.example.jxm.listviewsample.bean.Info3;
import com.example.jxm.listviewsample.hook.HookViewClick;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<IData>datas;
    private RecycleAdapter adapter;
    private RecyclerView recyclerView;
    private Button testBtn;
    CustomFlowLayout mViewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleView);
        mViewGroup=findViewById(R.id.viewgroup);
        initDatas();

        adapter=new RecycleAdapter(datas,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CustomItemDecoration decoration=new CustomItemDecoration();
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ListView listView=new ListView(this);

        testBtn=findViewById(R.id.test);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                datas.remove(2);
//                adapter.notifyItemRemoved(2);
//
//                TextView tv=new TextView(MainActivity.this);
//                tv.setText("新加了一个tv");
//                tv.setLayoutParams(new CustomFlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                mViewGroup.addView(tv);
                startActivity(new Intent(MainActivity.this,TestActivity.class));
                //SystemClock.sleep(600);
                //startActivity(new Intent(MainActivity.this,TestActivity.class));
            }
        });
    }

    void initDatas(){
        datas=new ArrayList<>();
        Info1 info1=new Info1();
        info1.setCenter_str("种类1");
        info1.setRight_str("右边");
        info1.setLeft_icon_id(R.mipmap.ic_launcher);

        Info2 info2=new Info2();
        info2.setTitle("卡片2");

        Info3 info3=new Info3();
        info3.setCenter_str("3");
        info3.setRight_str("右边");
        info3.setLeft_icon_id(R.mipmap.ic_launcher);


        datas.add(info1);
        datas.add(info2);
        datas.add(info3);

        datas.add(info1);
        datas.add(info2);
        datas.add(info3);

        datas.add(info1);
        datas.add(info2);
        datas.add(info3);

        datas.add(info1);
        datas.add(info2);
        datas.add(info3);

        datas.add(info1);
        datas.add(info2);
        datas.add(info3);

        datas.add(info1);
        datas.add(info2);
        datas.add(info3);


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_DOWN:
                HookViewClick.getHookInstance().hookAllViews(getWindow().getDecorView());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
