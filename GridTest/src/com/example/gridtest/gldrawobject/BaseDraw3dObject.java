package com.example.gridtest.gldrawobject;

import javax.microedition.khronos.opengles.GL10;

/**
 * GL �ŕ`�悷��I�u�W�F�N�g�̊��N���X
 *
 */
public abstract class BaseDraw3dObject {

    /** ���_���W�̎�������\���܂�. */
    protected static final int VERTEX_DIMENSION_NUM = 3;
    /** �e�N�X�`�����W�̎�������\���܂�. */
    protected static final int TEXTURE_DIMENSION_NUM = 2;

    /**
     * �`�惁�\�b�h
     * @param gl GL �C���X�^���X
     */
    public abstract void draw(GL10 gl);
}
