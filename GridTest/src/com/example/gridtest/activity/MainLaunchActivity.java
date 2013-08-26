package com.example.gridtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.gridtest.R;

/**
 * ���C����� �e��ʂւ̐U�蕪�����s�����C�����
 * 
 */
public class MainLaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_launch);

        mActivity = this;

        // �N���b�N�C�x���g�̐ݒ�
        MainLaunchClickListener listener = new MainLaunchClickListener();
        findViewById(R.id.button_cube).setOnClickListener(listener);
        findViewById(R.id.button_rotate_tile).setOnClickListener(listener);
        findViewById(R.id.button_splite).setOnClickListener(listener);

    }

    private class MainLaunchClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
            case R.id.button_cube:
                // �����̕\���e�X�g��ʂ֑J�ڂ��܂�.
                intent = new Intent(mActivity, GLCubeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_rotate_tile:
                // �^�C����]��ʂ֑J�ڂ��܂�.
                intent = new Intent(mActivity, GLRotateTileActivity.class);
                startActivity(intent);
                break;
            case R.id.button_splite:
                // �X�v���C�g�\����ʂ֑J�ڂ��܂�.
                intent = new Intent(mActivity, GLSpliteActivity.class);
                startActivity(intent);
                break;
            }
        }

    }
}
