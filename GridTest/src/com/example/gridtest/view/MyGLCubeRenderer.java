package com.example.gridtest.view;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.example.gridtest.gldrawobject.Cube;

/**
 * 立方体の描画を行います.
 * onSurfaceChanged,onSurfaceCreated は継承元クラスのままでよいため、
 * オーバーライドしていません.
 *
 */
public class MyGLCubeRenderer extends MyGLBaseRenderer {

	public MyGLCubeRenderer(Context context) {
        super(context);
    }

    /** 1周の角度を表します. */
	private static final float ROTATE_DEGREES = 360.0f;

	Cube mCube = new Cube();

	/** X軸の回転数を表します. */
	private float mRotateX = 0.0f;
	/** Y軸の回転数を表します. */
	private float mRotateY = 0.0f;

	@Override
	public void onDrawFrame(GL10 gl) {

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		// カメラが原点にいるため、オブジェクトをZ軸方向に少しだけずらす.
		gl.glTranslatef(0, 0, -3f);

		// 角度を加算します. ROTATE_DEGREES以上の値にならないよう 剰余算をしています.
		mRotateX = (mRotateX + 0.5f) % ROTATE_DEGREES;
		mRotateY = (mRotateY + 0.5f) % ROTATE_DEGREES;

		// 立方体を回転.
	    gl.glRotatef(mRotateX, 0, 1, 0);
	    gl.glRotatef(mRotateY, 1, 0, 0);

	    // 立方体を描画.
	    mCube.draw(gl);

	}
}