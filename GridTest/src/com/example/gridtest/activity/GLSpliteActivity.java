package com.example.gridtest.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.gridtest.view.MyGLView;

public class GLSpliteActivity extends Activity {
    MyGLView myGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGLView = new MyGLView(this, MyGLView.TYPE_SPLITE);
        setContentView(myGLView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myGLView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myGLView.onPause();
    }

}
