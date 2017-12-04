package com.walton.myvideoview.view;

import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.widget.RelativeLayout;


/**
 * Created by waltonmis on 2017/8/15.
 */

public class MySkyVideoView extends RelativeLayout{
    private VideoPlayerView videoPlayerView;
    private MediaControllerView mediaControllerView;
    public MySkyVideoView(Context context) {
        super(context);
        setGravity(Gravity.CENTER_HORIZONTAL);
        videoPlayerView = new VideoPlayerView(context);
        addView(videoPlayerView);
        mediaControllerView = new MediaControllerView(context);
        mediaControllerView.setVideoView(videoPlayerView);
        addView(mediaControllerView);
    }
    public void setUri(Uri uri){
        videoPlayerView.setUri(uri);
    }
    public void setLeftOnClickListener(OnClickListener onClickListener){
        mediaControllerView.setLeftOnClickListener(onClickListener);
    }
    public void setLeftOnDoubleListener(OnClickListener onDoubleListener){
        mediaControllerView.setLeftOnDoubleListener(onDoubleListener);
    }
    public void setLeftOnLongClickListener(OnLongClickListener onLongClickListener){
        mediaControllerView.setLeftOnLongClickListener(onLongClickListener);
    }
    public void setRightOnClickListener(OnClickListener onClickListener){
        mediaControllerView.setRightOnClickListener(onClickListener);
    }
    public void setRightOnDoubleListener(OnClickListener onDoubleListener){
        mediaControllerView.setRightOnDoubleListener(onDoubleListener);
    }
    public void setRightOnLongClickListener(OnLongClickListener onLongClickListener){
        mediaControllerView.setRightOnLongClickListener(onLongClickListener);
    }
    public void setMiddleOnClickListener(OnClickListener onClickListener){
        mediaControllerView.setMiddleOnClickListener(onClickListener);
    }
    public void setMiddleOnDoubleListener(OnClickListener onDoubleListener){
        mediaControllerView.setMiddleOnDoubleListener(onDoubleListener);
    }
    public void setMiddleOnLongClickListener(OnLongClickListener onLongClickListener){
        mediaControllerView.setMiddleOnLongClickListener(onLongClickListener);
    }
}

