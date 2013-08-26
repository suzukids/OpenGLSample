package com.example.gridtest.gldrawobject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class Tile extends BaseDraw3dObject {

    /** 頂点座標用バッファ */
    private final FloatBuffer mVertexBuffer;
    /** テクスチャ座標用バッファ */
    private final FloatBuffer mTextureBuffer;

    /** テクスチャNoを表します. */
    public int textureNo;

    /** 各頂点座標を表します. */
    float mVertices[] = {
            -0.5f, -0.5f, 0f,
             0.5f, -0.5f, 0f,
            -0.5f,  0.5f, 0f,
             0.5f,  0.5f, 0f, };

    /** 各頂点のテクスチャ座標 を表します. */
    float textureVertices[] = {
            1.0f, 1.0f,
            0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 0.0f, };

    /**
     * コンストラクタ
     */
    public Tile() {

        // 頂点座標の初期化.
        mVertexBuffer = ByteBuffer.allocateDirect(mVertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

        mVertexBuffer.put(mVertices);
        mVertexBuffer.position(0);

        // テクスチャ座標の初期化.
        mTextureBuffer = ByteBuffer.allocateDirect(textureVertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

        mTextureBuffer.put(textureVertices);
        mTextureBuffer.position(0);
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
        // テクスチャ座標が1.0fを超えたときの、テクスチャを繰り返す設定
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
        // テクスチャを元のサイズから拡大、縮小して使用したときの色の使い方を設定
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
    }

    public void draw(GL10 gl) {

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // 色を設定
        gl.glColor4f(0.f, 1.f, 0.f, 0.5f);

        // 頂点情報を設定
        gl.glVertexPointer(VERTEX_DIMENSION_NUM, GL10.GL_FLOAT, 0, this.mVertexBuffer);
        gl.glTexCoordPointer(TEXTURE_DIMENSION_NUM, GL10.GL_FLOAT, 0, this.mTextureBuffer);

        // 三角形を描く
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, this.mVertices.length / 3);

        // 有効にしたモノを元に戻す
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

}
