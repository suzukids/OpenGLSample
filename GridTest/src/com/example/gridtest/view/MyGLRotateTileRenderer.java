package com.example.gridtest.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;

import com.example.gridtest.R;
import com.example.gridtest.gldrawobject.Tile;

/**
 * 立方体の描画を行います. onSurfaceChanged,onSurfaceCreated は継承元クラスのままでよいため、
 * オーバーライドしていません.
 * 
 */
public class MyGLRotateTileRenderer extends MyGLBaseRenderer {

    public MyGLRotateTileRenderer(Context context) {
        super(context);
    }

    /** 1周の角度を表します. */
    private static final float ROTATE_DEGREES = 360.0f;

    Tile mTile = new Tile();

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
//        gl.glTranslatef(0, 0, -3.0f);
        
        GLU.gluLookAt(gl, 0.0f, 0.0f, 3.0f, // カメラの位置
                0.0f, 0.0f, 1.0f, // カメラの向いている方向
                0.0f, 1.0f, 0.0f); // カメラの上方向のベクトル

        // 角度を加算します. ROTATE_DEGREES以上の値にならないよう 剰余算をしています.
        mRotateX = (mRotateX + 0.5f) % ROTATE_DEGREES;
        mRotateY = (mRotateY + 0.5f) % ROTATE_DEGREES;

        // タイルを回転.
        gl.glRotatef(mRotateX, 0, 1, 0);
//        gl.glRotatef(mRotateY, 1, 0, 0);

        // タイルを描画.
        mTile.draw(gl);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);

      // 背景色をクリア
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        // ディザを無効化
        gl.glDisable(GL10.GL_DITHER);
        // テクスチャ機能ON
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // 透明可能に
        gl.glEnable(GL10.GL_ALPHA_TEST);
        // ブレンド可能に
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        // テクスチャの合成環境の設定.
        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);
        
        mTile.setTexture(gl, mContext.getResources(), R.drawable.texture_doroid);
    }
}