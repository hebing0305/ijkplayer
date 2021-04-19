package com.kjsc.ijkplayer;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

import tv.danmaku.ijk.media.player.IMediaPlayer;


public class VideoViewForTv extends IjkVideoView {
    TextView tvNowTime, tvAllTime;
    SeekBar seekBar;
    boolean isUpdateSeekBar = true;
    ImageView ivPlayStatus;
    LinearLayout control;
    FrameLayout title_root;
    TextView title;

    public static final int UPDATE_WHAT = 0;
    public static final int HIDE_PLAY_STATUS_WHAT = 1;
    public static final int HIDE_CONTROL_WHAT = 2;

    public VideoViewForTv(@NonNull Context context) {
        super(context);
        init();
    }

    public VideoViewForTv(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoViewForTv(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.video_view_for_tv, this);
        tvNowTime = findViewById(R.id.nowTime);
        tvAllTime = findViewById(R.id.allTime);
        seekBar = findViewById(R.id.seek_bar);
        ivPlayStatus = findViewById(R.id.play_status);
        title = findViewById(R.id.title);
        title_root = findViewById(R.id.title_root);
        control = findViewById(R.id.control);
        isShowControl(false);
        setFocusable(true);
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

    public String millis2String(long mills) {
        return simpleDateFormat.format(new Date(mills));
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UPDATE_WHAT:
                    if (isPlaying()) {
                        int duration = getDuration();
                        if (duration != seekBar.getMax()) {
                            String allTime = millis2String(duration);
                            seekBar.setMax(duration);
                            tvAllTime.setText(allTime);
                        }

                        int currentPosition = getCurrentPosition();
                        String curTime = millis2String(currentPosition);
                        tvNowTime.setText(curTime);
                        if (isUpdateSeekBar) {
                            seekBar.setProgress(currentPosition);
                        }
                    }
                    handler.sendEmptyMessageDelayed(UPDATE_WHAT, 500);
                    break;
                case HIDE_PLAY_STATUS_WHAT:
                    ivPlayStatus.setVisibility(GONE);
                    break;
                case HIDE_CONTROL_WHAT:
                    isShowControl(false);
                    break;
            }
            return false;
        }
    });

    @Override
    public void onPrepared(IMediaPlayer mp) {
        super.onPrepared(mp);
        start();
        handler.sendEmptyMessage(UPDATE_WHAT);
        isShowControl(true);
        seekBar.requestFocus();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("dispatchKeyEvent isFocused=" + seekBar.isFocused());
        handler.removeMessages(HIDE_CONTROL_WHAT);
        isShowControl(true);
        int action = event.getAction();
        int keycode = event.getKeyCode();
        if (action == KeyEvent.ACTION_DOWN && (keycode == KeyEvent.KEYCODE_DPAD_LEFT || keycode == KeyEvent.KEYCODE_DPAD_RIGHT)) {
            if (isNoControl) {
                return true;
            }
            isUpdateSeekBar = false;
        } else if (event.getAction() == KeyEvent.ACTION_UP && (keycode == KeyEvent.KEYCODE_DPAD_LEFT || keycode == KeyEvent.KEYCODE_DPAD_RIGHT)) {
            if (isNoControl) {
                return true;
            }
            isUpdateSeekBar = true;
            seekTo(seekBar.getProgress());
            start();
        }

        if (action == KeyEvent.ACTION_DOWN) {
            switch (keycode) {
                case KeyEvent.KEYCODE_DPAD_CENTER:
                case KeyEvent.KEYCODE_ENTER:
                    if (isPlaying()) {
                        pause();
                    } else {
                        start();
                    }
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void pause() {
        super.pause();
        ivPlayStatus.setVisibility(VISIBLE);
        ivPlayStatus.setImageResource(R.mipmap.icon_player);
    }

    @Override
    public void start() {
        super.start();
        if (ivPlayStatus.isShown()) {
            ivPlayStatus.setImageResource(R.mipmap.icon_pause);
        }
        handler.sendEmptyMessageDelayed(HIDE_PLAY_STATUS_WHAT, 500);
    }

    public void isShowControl(boolean isShow) {
        control.setVisibility(isShow ? VISIBLE : INVISIBLE);
        title_root.setVisibility(isShow ? VISIBLE : INVISIBLE);
        if (isShow) {
            seekBar.requestFocus();
            handler.sendEmptyMessageDelayed(HIDE_CONTROL_WHAT, 3000);
        }
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    boolean isNoControl = false;

    public void setNoControl(boolean noControl) {
        isNoControl = noControl;
    }

}
