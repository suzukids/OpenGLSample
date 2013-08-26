package com.example.gridtest.view;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.example.gridtest.gldrawobject.Cube;

/**
 * �����̂̕`����s���܂�.
 * onSurfaceChanged,onSurfaceCreated �͌p�����N���X�̂܂܂ł悢���߁A
 * �I�[�o�[���C�h���Ă��܂���.
 *
 */
public class MyGLCubeRenderer extends MyGLBaseRenderer {

	public MyGLCubeRenderer(Context context) {
        super(context);
    }

    /** 1���̊p�x��\���܂�. */
	private static final float ROTATE_DEGREES = 360.0f;

	Cube mCube = new Cube();

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
		gl.glTranslatef(0, 0, -3f);

		// �p�x�����Z���܂�. ROTATE_DEGREES�ȏ�̒l�ɂȂ�Ȃ��悤 ��]�Z�����Ă��܂�.
		mRotateX = (mRotateX + 0.5f) % ROTATE_DEGREES;
		mRotateY = (mRotateY + 0.5f) % ROTATE_DEGREES;

		// �����̂���].
	    gl.glRotatef(mRotateX, 0, 1, 0);
	    gl.glRotatef(mRotateY, 1, 0, 0);

	    // �����̂�`��.
	    mCube.draw(gl);

	}
}