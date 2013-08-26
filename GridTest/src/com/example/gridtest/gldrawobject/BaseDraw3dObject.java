package com.example.gridtest.gldrawobject;

import javax.microedition.khronos.opengles.GL10;

/**
 * GL で描画するオブジェクトの基底クラス
 *
 */
public abstract class BaseDraw3dObject {

    /** 頂点座標の次元数を表します. */
    protected static final int VERTEX_DIMENSION_NUM = 3;
    /** テクスチャ座標の次元数を表します. */
    protected static final int TEXTURE_DIMENSION_NUM = 2;

    /**
     * 描画メソッド
     * @param gl GL インスタンス
     */
    public abstract void draw(GL10 gl);
}
