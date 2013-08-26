package com.example.gridtest.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class MyGLView extends GLSurfaceView {

    public static final int TYPE_CUBE = 0;
    public static final int TYPE_ROTATE_TILE = 1;
    public static final int TYPE_SPLITE = 2;

    MyGLBaseRenderer mRenderer;

    public MyGLView(Context context, int type) {
        super(context);

        switch (type) {
        case TYPE_CUBE:
            mRenderer = new MyGLCubeRenderer(context);
            break;
        case TYPE_ROTATE_TILE:
            mRenderer = new MyGLRotateTileRenderer(context);
            break;
        case TYPE_SPLITE:
            mRenderer = new MyGLSpliteRenderer(context);
            break;
        }
        setRenderer(mRenderer);
    }

}