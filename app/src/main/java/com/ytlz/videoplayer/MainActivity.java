package com.ytlz.videoplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.MediaController;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class MainActivity extends AppCompatActivity {

    JzvdStd jzvdStd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {
//         jzvdStd = (JzvdStd) findViewById(R.id.jz_video);
//        jzvdStd.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
//                , "饺子闭眼睛");
//        jzvdStd.thumbImageView.setImageResource(R.mipmap.ic_launcher);

//        jzvdStd.setScreenFullscreen();
        //jzvdStd.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");



    }

    @Override
    protected void onResume() {
        super.onResume();
        JzvdStd.goOnPlayOnResume();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.goOnPlayOnPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清除所有的缓存进度
        JzvdStd.clearSavedProgress(this, null);

    }

    public void startMediaControllerActivity(View view) {
        startActivity(new Intent(MainActivity.this, MediaControllerActivity.class));
    }

    public void startVideoActivity(View view) {
        startActivity(new Intent(MainActivity.this, VideoviewActivity.class));

    }
}
