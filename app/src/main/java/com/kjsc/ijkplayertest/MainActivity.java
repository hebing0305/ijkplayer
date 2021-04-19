package com.kjsc.ijkplayertest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kjsc.ijkplayer.IjkVideoView;
import com.kjsc.ijkplayer.VideoViewForTv;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    //    String url = "http://219.152.49.2:8088/kjsc/2021041616341064975767.mp4";//4k视频
    //        String url = "https://media.w3.org/2010/05/sintel/trailer.mp4";
    //http://219.152.49.2:8088/kjsc/2021040916235592619742.mp4
    //http://vjs.zencdn.net/v/oceans.mp4
//    String url = "http://219.152.49.2:8088/kjsc/2021040916235592619742.mp4";
    String url = "http://219.152.49.2:8088/kjsc/2021041616565171623286.mp4";//高帧率视频
//        String url = "http://101.132.132.202:8080/aqua/rest/cdmi/default/netdisk/upgradeuser/recommendation_config/channellist/%E6%B7%B1%E5%9C%B3%E5%8D%AB%E8%A7%86_%E9%80%86%E8%A2%AD%E4%B9%8B%E6%98%9F%E9%80%94%E7%92%80%E7%92%A8.mp4";
    VideoViewForTv mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = findViewById(R.id.ijkplayer);
        mVideoView.setVideoPath(url);
        mVideoView.setHudView(findViewById(R.id.table_layout));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopAndRelease();
    }
}