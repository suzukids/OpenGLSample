package com.example.gridtest.activity;

import com.example.gridtest.view.MyGLView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GLRotateTileActivity extends Activity {

	MyGLView myGLView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myGLView = new MyGLView(this, MyGLView.TYPE_ROTATE_TILE);
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