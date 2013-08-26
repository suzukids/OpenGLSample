package com.example.gridtest.gldrawobject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

/**
 * 2D描画クラス
 *
 */
public class Splite {

    private static final int VERTEX_DIMENSION_NUM = 3;

    /** 頂点座標用バッファ */
    private final FloatBuffer mVertexBuffer;

    /** テクスチャNoを表します. */
    public int textureNo;

    /** 各頂点座標を表します. */
    private float  mVertices[] = { 0.8f, 0.8f, 0 };

    /**
     * コンストラクタ
     */
    public Splite() {

        // 頂点座標の初期化.
        mVertexBuffer = ByteBuffer.allocateDirect(mVertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertexBuffer.put(mVertices);
        mVertexBuffer.position(0);
    }

    /**
     * テクスチャの読み込み.
     */
    public void setTexture(GL10 gl, Resources res, int id) {
        // テクスチャ用リソースファイルの読み込み.
        Bitmap bitmap = BitmapFactory.decodeResource(res, id);

        // テクスチャIDを割り当てる
        int[] textureID = new int[1];
        gl.glGenTextures(1, textureID, 0);
        textureNo = textureID[0];

        // テクスチャIDのバインド
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureNo);

        // OpenGL ES用のメモリ領域に画像データを渡す。上でバインドされたテクスチャIDと結び付けられる。
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        // テクスチャフィルタの指定
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
    }

    public void draw(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // 頂点情報を設定
        gl.glVertexPointer(VERTEX_DIMENSION_NUM, GL10.GL_FLOAT, 0, this.mVertexBuffer);
        gl.glActiveTexture(GL10.GL_TEXTURE0);
        gl.glTexEnvf(GL11.GL_POINT_SPRITE_OES,
            GL11.GL_COORD_REPLACE_OES,GL10.GL_TRUE);
        // スプライトを描く
        gl.glPointSize(512.0f);
        gl.glDrawArrays(GL10.GL_POINTS, 0, this.mVertices.length / 3);

        // 有効にしたモノを元に戻す
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
