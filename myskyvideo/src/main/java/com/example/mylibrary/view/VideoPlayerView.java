package com.example.mylibrary.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.mylibrary.listener.VideoOnCompletionListener;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class VideoPlayerView extends LinearLayout{
    VideoView videoView;
    MediaController mediaController;
    VideoOnCompletionListener videoOnCompletionListener;
    public VideoPlayerView(Context context) {
        super(context);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setGravity(Gravity.CENTER);
        videoView = new VideoView(context);
        videoView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
    public void setUri(Uri uri){
        videoView.setVideoURI(uri);
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

