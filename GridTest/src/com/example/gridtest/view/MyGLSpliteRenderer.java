package com.example.gridtest.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.content.Context;

import com.example.gridtest.R;
import com.example.gridtest.gldrawobject.Splite;

/**
 * 2D スプライトの描画を行います.
 */
public class MyGLSpliteRenderer extends MyGLBaseRenderer {

    // コンテキスト
    private Context mContext;

    public MyGLSpliteRenderer(Context context) {
        super(context);
        mContext = context;
    }

    Splite mSplite = new Splite();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // super ではライトの設定などを行っているため、スプライト描画の場合はsuper クラスのメソッドはコールしない.
        // super.onSurfaceCreated(gl, config);

        // テクスチャ機能ON
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // ポイントスプライトを可能に
        gl.glEnable(GL11.GL_POINT_SPRITE_OES);
        // ブレンド可能に
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        mSplite.setTexture(gl, mContext.getResources(), R.drawable.texture_doroid);

        // 頂点配列の有効化
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 画面の表示領域の指定
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
         gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        
         // スプライトを描画.
         mSplite.draw(gl);
    }
}