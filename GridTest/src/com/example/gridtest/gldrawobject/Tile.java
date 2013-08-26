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

    /** ���_���W�p�o�b�t�@ */
    private final FloatBuffer mVertexBuffer;
    /** �e�N�X�`�����W�p�o�b�t�@ */
    private final FloatBuffer mTextureBuffer;

    /** �e�N�X�`��No��\���܂�. */
    public int textureNo;

    /** �e���_���W��\���܂�. */
    float mVertices[] = {
            -0.5f, -0.5f, 0f,
             0.5f, -0.5f, 0f,
            -0.5f,  0.5f, 0f,
             0.5f,  0.5f, 0f, };

    /** �e���_�̃e�N�X�`�����W ��\���܂�. */
    float textureVertices[] = {
            1.0f, 1.0f,
            0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 0.0f, };

    /**
     * �R���X�g���N�^
     */
    public Tile() {

        // ���_���W�̏�����.
        mVertexBuffer = ByteBuffer.allocateDirect(mVertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

        mVertexBuffer.put(mVertices);
        mVertexBuffer.position(0);

        // �e�N�X�`�����W�̏�����.
        mTextureBuffer = ByteBuffer.allocateDirect(textureVertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

        mTextureBuffer.put(textureVertices);
        mTextureBuffer.position(0);
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
        // �e�N�X�`�����W��1.0f�𒴂����Ƃ��́A�e�N�X�`�����J��Ԃ��ݒ�
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
        // �e�N�X�`�������̃T�C�Y����g��A�k�����Ďg�p�����Ƃ��̐F�̎g������ݒ�
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
    }

    public void draw(GL10 gl) {

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // �F��ݒ�
        gl.glColor4f(0.f, 1.f, 0.f, 0.5f);

        // ���_����ݒ�
        gl.glVertexPointer(VERTEX_DIMENSION_NUM, GL10.GL_FLOAT, 0, this.mVertexBuffer);
        gl.glTexCoordPointer(TEXTURE_DIMENSION_NUM, GL10.GL_FLOAT, 0, this.mTextureBuffer);

        // �O�p�`��`��
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, this.mVertices.length / 3);

        // �L���ɂ������m�����ɖ߂�
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

}
