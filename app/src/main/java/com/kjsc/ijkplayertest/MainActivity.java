package com.kjsc.ijkplayertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kjsc.ijkplayer.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    IjkVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        IjkVideoView mVideoView = findViewById(R.id.ijkplayer);
        mVideoView.setVideoPath("http://101.132.132.202:8080/aqua/rest/cdmi/default/netdisk/upgradeuser/recommendation_config/channellist/%E5%AE%89%E5%BE%BD%E5%8D%AB%E8%A7%86%E9%AB%98%E6%B8%85.mp4");
        mVideoView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
        mVideoView.release(true);
        mVideoView.stopBackgroundPlay();
    }
}