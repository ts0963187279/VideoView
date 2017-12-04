package com.walton.myvideoview.listener;

import android.media.MediaPlayer;

import com.walton.myvideoview.view.VideoPlayerView;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class VideoOnCompletionListener implements MediaPlayer.OnCompletionListener {
    VideoPlayerView view;
    public void setView(VideoPlayerView view){
        this.view = view;
    }
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        view.controllerShow(1000000);
        view.setTime(0);
    }
}