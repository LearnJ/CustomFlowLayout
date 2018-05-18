package com.example.jxm.listviewsample;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private final String TAG="TestActivity";
    private TextView go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.i(TAG, "jm--onCreate: "+this.hashCode());
        go=findViewById(R.id.go);
        SystemClock.sleep(400);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this,TestActivity.class));
            }
        });
    }


//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        Log.i(TAG, "jm--onPostResume: "+getClass().getName());
//    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "jm--onPause: "+this.hashCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "jm--onStart: "+this.hashCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "jm--onResume: "+this.hashCode());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "jm--onStop: "+this.hashCode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "jm--onDestroy: "+this.hashCode());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "jm--onRestart: "+this.hashCode());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "jm--onNewIntent: "+this.hashCode());
    }
}
