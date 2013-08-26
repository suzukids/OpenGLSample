package com.example.gridtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.gridtest.R;

/**
 * メイン画面 各画面への振り分けを行うメイン画面
 * 
 */
public class MainLaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_launch);

        mActivity = this;

        // クリックイベントの設定
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
                // 立方体表示テスト画面へ遷移します.
                intent = new Intent(mActivity, GLCubeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_rotate_tile:
                // タイル回転画面へ遷移します.
                intent = new Intent(mActivity, GLRotateTileActivity.class);
                startActivity(intent);
                break;
            case R.id.button_splite:
                // スプライト表示画面へ遷移します.
                intent = new Intent(mActivity, GLSpliteActivity.class);
                startActivity(intent);
                break;
            }
        }

    }
}
