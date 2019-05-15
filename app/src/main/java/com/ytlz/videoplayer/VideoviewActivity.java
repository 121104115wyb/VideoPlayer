package com.ytlz.videoplayer;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoviewActivity extends AppCompatActivity {

    private static final String TAG = "VideoviewActivity";

    private static String videoUrlString = "http://jzvd.nathen.cn/6ea7357bc3fa4658b29b7933ba575008/fbbba953374248eb913cb1408dc61d85-5287d2089db37e62345123a1be272f8b.mp4";

    private String[] urlVideos = {"http://jzvd.nathen.cn/6ea7357bc3fa4658b29b7933ba575008/fbbba953374248eb913cb1408dc61d85-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/35b3dc97fbc240219961bd1fccc6400b/8d9b76ab5a584bce84a8afce012b72d3-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/df6096e7878541cbbea3f7298683fbed/ef76450342914427beafe9368a4e0397-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/384d341e000145fb82295bdc54ecef88/103eab5afca34baebc970378dd484942-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/f55530ba8a59403da0621cbf4faef15e/adae4f2e3ecf4ea780beb057e7bce84c-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/6340efd1962946ad80eeffd19b3be89c/65b499c0f16e4dd8900497e51ffa0949-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/f07fa9fddd1e45a6ae1570c7fe7967c1/c6db82685b894e25b523b1cb28d79f2e-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/d2e969f2ec734520b46ab0965d2b68bd/f124edfef6c24be8b1a7b7f996ccc5e0-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/4f965ad507ef4194a60a943a34cfe147/32af151ea132471f92c9ced2cff785ea-5287d2089db37e62345123a1be272f8b.mp4",
            "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"};
    private String[] titles = {"测试", "德玛西亚", "121313131", "ertertegvdvssfs", "我的天啊", "测试", "德玛西亚", "121313131", "ertertegvdvssfs", "我的天啊"};

    private VideoView videoView;

    private Button lastV;
    private Button startV;
    private Button nextV;
    private ImageView backImg;
    private TextView videoName;

    private int index = 0;
    private CountDownTimer countDownTimer;

    private static long COUNT_DOWN_HIDE_TIME = 3000L;

    private LinearLayout videoTitleLayout;
    private RelativeLayout bottom_layout;
    private Boolean isCounting = false;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_videoview);
        initViews();

    }

    void initViews() {
        videoTitleLayout = findViewById(R.id.back_layout);
        bottom_layout = findViewById(R.id.bottom_layout);
        videoView = findViewById(R.id.video_view);
        lastV = findViewById(R.id.lastbtn);
        startV = findViewById(R.id.start);
        nextV = findViewById(R.id.next);
        backImg = findViewById(R.id.backImg);
        videoName = findViewById(R.id.video_name);
        seekBar = findViewById(R.id.seekbar);
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (videoTitleLayout.getVisibility() == View.GONE) {
                            videoTitleLayout.setVisibility(View.VISIBLE);
                        }
                        if (bottom_layout.getVisibility() == View.GONE) {
                            bottom_layout.setVisibility(View.VISIBLE);
                        }
                        if (null != countDownTimer & !isCounting)
                            countDownTimer.start();
                        break;
                }
                return false;
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: ---progress"+progress+"---fromUser--"+fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: ");
            }
        });


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "onPrepared: ----seekBar.size:"+mp.getDuration());
            }
        });

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {


                Log.d(TAG, "onInfo: ---what:"+what+"extra:"+extra);
                return false;
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                Log.d(TAG, "onCompletion: ----->mp"+mp.getDuration()+"----"+mp.getCurrentPosition());

            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                Log.d(TAG, "onError: ---what"+what);
                return false;
            }
        });



        lastV.setOnClickListener(v -> {
            if (index > 0 & index < urlVideos.length - 1) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                videoView.setVideoPath(urlVideos[index - 1]);
                videoName.setText(titles[index - 1]);
                videoView.start();

//                int aaa = index--;

                Log.d(TAG, "initViews: --index:" + index + "index--:" + index--);
            }
        });

        startV.setOnClickListener(v -> {

            if ("播放".equalsIgnoreCase(startV.getText().toString())) {
                if (null != videoView && !videoView.isPlaying()) {
                    videoView.setVideoPath(urlVideos[index]);
                    videoName.setText(titles[index]);
                    videoView.start();
                    startV.setText("暂停");
                }
            } else {
                if (null != videoView && !videoView.isPlaying()) {
                    videoView.resume();
                    videoView.start();
                    startV.setText("暂停");
                }
                if (videoView.isPlaying()){
                    videoView.pause();
                    startV.setText("播放");
                }
            }
        });

        nextV.setOnClickListener(v -> {
            if (index >= 0 & index < urlVideos.length - 2) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                videoView.setVideoPath(urlVideos[index + 1]);
                videoName.setText(titles[index + 1]);
                videoView.start();
                index++;
            }
        });

        backImg.setOnClickListener(v -> {
            finish();
        });

        countDownTimer = new CountDownTimer(COUNT_DOWN_HIDE_TIME, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (videoTitleLayout.getVisibility() == View.VISIBLE) {
                    videoTitleLayout.setVisibility(View.GONE);
                }
                if (bottom_layout.getVisibility() == View.VISIBLE) {
                    bottom_layout.setVisibility(View.GONE);
                }
                isCounting = false;
            }
        };
//        countDownTimer.start();
//        isCounting = true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (null != videoView) {
////            videoView.resume();
//            videoView.start();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != videoView) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != videoView) {
            videoView.pause();
            videoView.stopPlayback();
        }
    }
}
