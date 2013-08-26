package com.example.gridtest.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.content.Context;

import com.example.gridtest.R;
import com.example.gridtest.gldrawobject.Splite;

/**
 * 2D �X�v���C�g�̕`����s���܂�.
 */
public class MyGLSpliteRenderer extends MyGLBaseRenderer {

    // �R���e�L�X�g
    private Context mContext;

    public MyGLSpliteRenderer(Context context) {
        super(context);
        mContext = context;
    }

    Splite mSplite = new Splite();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // super �ł̓��C�g�̐ݒ�Ȃǂ��s���Ă��邽�߁A�X�v���C�g�`��̏ꍇ��super �N���X�̃��\�b�h�̓R�[�����Ȃ�.
        // super.onSurfaceCreated(gl, config);

        // �e�N�X�`���@�\ON
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // �|�C���g�X�v���C�g���\��
        gl.glEnable(GL11.GL_POINT_SPRITE_OES);
        // �u�����h�\��
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        mSplite.setTexture(gl, mContext.getResources(), R.drawable.texture_doroid);

        // ���_�z��̗L����
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // ��ʂ̕\���̈�̎w��
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
         gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        
         // �X�v���C�g��`��.
         mSplite.draw(gl);
    }
}