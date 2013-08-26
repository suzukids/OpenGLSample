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
 * 2D�`��N���X
 *
 */
public class Splite {

    private static final int VERTEX_DIMENSION_NUM = 3;

    /** ���_���W�p�o�b�t�@ */
    private final FloatBuffer mVertexBuffer;

    /** �e�N�X�`��No��\���܂�. */
    public int textureNo;

    /** �e���_���W��\���܂�. */
    private float  mVertices[] = { 0.8f, 0.8f, 0 };

    /**
     * �R���X�g���N�^
     */
    public Splite() {

        // ���_���W�̏�����.
        mVertexBuffer = ByteBuffer.allocateDirect(mVertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertexBuffer.put(mVertices);
        mVertexBuffer.position(0);
    }

    /**
     * �e�N�X�`���̓ǂݍ���.
     */
    public void setTexture(GL10 gl, Resources res, int id) {
        // �e�N�X�`���p���\�[�X�t�@�C���̓ǂݍ���.
        Bitmap bitmap = BitmapFactory.decodeResource(res, id);

        // �e�N�X�`��ID�����蓖�Ă�
        int[] textureID = new int[1];
        gl.glGenTextures(1, textureID, 0);
        textureNo = textureID[0];

        // �e�N�X�`��ID�̃o�C���h
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureNo);

        // OpenGL ES�p�̃������̈�ɉ摜�f�[�^��n���B��Ńo�C���h���ꂽ�e�N�X�`��ID�ƌ��ѕt������B
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        // �e�N�X�`���t�B���^�̎w��
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
    }

    public void draw(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // ���_����ݒ�
        gl.glVertexPointer(VERTEX_DIMENSION_NUM, GL10.GL_FLOAT, 0, this.mVertexBuffer);
        gl.glActiveTexture(GL10.GL_TEXTURE0);
        gl.glTexEnvf(GL11.GL_POINT_SPRITE_OES,
            GL11.GL_COORD_REPLACE_OES,GL10.GL_TRUE);
        // �X�v���C�g��`��
        gl.glPointSize(512.0f);
        gl.glDrawArrays(GL10.GL_POINTS, 0, this.mVertices.length / 3);

        // �L���ɂ������m�����ɖ߂�
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
