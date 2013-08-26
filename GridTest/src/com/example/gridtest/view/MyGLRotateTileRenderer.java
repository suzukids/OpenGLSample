package com.example.gridtest.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;

import com.example.gridtest.R;
import com.example.gridtest.gldrawobject.Tile;

/**
 * �����̂̕`����s���܂�. onSurfaceChanged,onSurfaceCreated �͌p�����N���X�̂܂܂ł悢���߁A
 * �I�[�o�[���C�h���Ă��܂���.
 * 
 */
public class MyGLRotateTileRenderer extends MyGLBaseRenderer {

    public MyGLRotateTileRenderer(Context context) {
        super(context);
    }

    /** 1���̊p�x��\���܂�. */
    private static final float ROTATE_DEGREES = 360.0f;

    Tile mTile = new Tile();

    /** X���̉�]����\���܂�. */
    private float mRotateX = 0.0f;
    /** Y���̉�]����\���܂�. */
    private float mRotateY = 0.0f;

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        // �J���������_�ɂ��邽�߁A�I�u�W�F�N�g��Z�������ɏ����������炷.
//        gl.glTranslatef(0, 0, -3.0f);
        
        GLU.gluLookAt(gl, 0.0f, 0.0f, 3.0f, // �J�����̈ʒu
                0.0f, 0.0f, 1.0f, // �J�����̌����Ă������
                0.0f, 1.0f, 0.0f); // �J�����̏�����̃x�N�g��

        // �p�x�����Z���܂�. ROTATE_DEGREES�ȏ�̒l�ɂȂ�Ȃ��悤 ��]�Z�����Ă��܂�.
        mRotateX = (mRotateX + 0.5f) % ROTATE_DEGREES;
        mRotateY = (mRotateY + 0.5f) % ROTATE_DEGREES;

        // �^�C������].
        gl.glRotatef(mRotateX, 0, 1, 0);
//        gl.glRotatef(mRotateY, 1, 0, 0);

        // �^�C����`��.
        mTile.draw(gl);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);

      // �w�i�F���N���A
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        // �f�B�U�𖳌���
        gl.glDisable(GL10.GL_DITHER);
        // �e�N�X�`���@�\ON
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // �����\��
        gl.glEnable(GL10.GL_ALPHA_TEST);
        // �u�����h�\��
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        // �e�N�X�`���̍������̐ݒ�.
        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);
        
        mTile.setTexture(gl, mContext.getResources(), R.drawable.texture_doroid);
    }
}