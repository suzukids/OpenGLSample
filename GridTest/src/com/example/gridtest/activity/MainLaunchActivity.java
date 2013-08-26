package com.example.gridtest.activity;

import com.example.gridtest.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * メイン画面 各画面への振り分けを行うメイン画面
 * 
 */
public class MainLaunchActivity extends BaseActivity {

    Button mButtonCube;
    Button mButtonRotateTile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_launch);

        mActivity = this;
        mButtonCube = (Button) findViewById(R.id.button_cube);
        mButtonRotateTile = (Button) findViewById(R.id.button_rotate_tile);

        // クリックイベントの設定
        MainLaunchClickListener listener = new MainLaunchClickListener();
        mButtonCube.setOnClickListener(listener);
        mButtonRotateTile.setOnClickListener(listener);

    }

    private class MainLaunchClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
            case R.id.button_cube:
                // 立方体表示テスト画面へ遷移します.
                intent = new Intent(mActivity, GLCubeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_rotate_tile:
                // タイル回転画面へ遷移します.
                intent = new Intent(mActivity, GLRotateTileActivity.class);
                startActivity(intent);
                break;
            }
        }

    }
}
