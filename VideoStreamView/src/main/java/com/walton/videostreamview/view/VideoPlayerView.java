package com.walton.videostreamview.view;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.walton.videostreamview.listener.VideoOnCompletionListener;

import java.io.File;

/**
 * Created by waltonmis on 2017/8/15.
 */


public class VideoPlayerView extends LinearLayout{
    private VideoView videoView;
    private MediaController mediaController;
    private VideoOnCompletionListener videoOnCompletionListener;
    public VideoPlayerView(Context context,File file) {
        super(context);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setGravity(Gravity.CENTER);
        videoView = new VideoView(context);
        videoView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        videoView.setVideoPath(file.getAbsolutePath());
        videoView.start();
        videoOnCompletionListener = new VideoOnCompletionListener();
        videoOnCompletionListener.setView(this);
        videoView.setOnCompletionListener(videoOnCompletionListener);
        mediaController = new MediaController(context);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        addView(videoView);
        videoView.requestFocus();
    }
    public void start(){
        videoView.start();
    }
    public int getTime(){
        return videoView.getCurrentPosition();
    }
    public void setTime(int time){
        videoView.seekTo(time);
    }
    public void pause(){
        videoView.pause();
    }
    public void controllerShow(int time){
        mediaController.show(time);
    }
}
