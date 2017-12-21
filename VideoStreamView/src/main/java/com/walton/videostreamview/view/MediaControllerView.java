package com.walton.videostreamview.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.walton.videostreamview.listener.OnDoubleClickListener;
import com.walton.videostreamview.listener.OnMoveHorizontallyListener;
import com.walton.videostreamview.listener.OnMoveVerticallyListener;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class MediaControllerView extends LinearLayout {
    private MediaControlButton middle,left,right;
    private MediaControllerButtonView mediaControllerButtonView;
    private VideoPlayerView videoPlayerView;
    public MediaControllerView(Context context) {
        super(context);
//        setGravity(Gravity.CENTER_HORIZONTAL);
//        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        setWeightSum(10);
//        left = new MediaControlButton(context,this);
//        middle = new MediaControlButton(context,this);
//        right = new MediaControlButton(context,this);
//        left.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,4.0f));
//        middle.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,2.0f));
//        right.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,4.0f));
//        left.setBackgroundColor(Color.argb(0,0,0,0));
//        middle.setBackgroundColor(Color.argb(0,0,0,0));
//        right.setBackgroundColor(Color.argb(0,0,0,0));
//        addView(left);
//        addView(middle);
//        addView(right);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mediaControllerButtonView = new MediaControllerButtonView(context,this);
        addView(mediaControllerButtonView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                System.out.println("MediaControllerView ==== ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("MediaControllerView ==== ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("MediaControllerView ==== ACTION_MOVE");
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setVideoView(VideoPlayerView videoPlayerView){
        this.videoPlayerView = videoPlayerView;
    }
    public void setTime(int time){
        videoPlayerView.setTime(time);
    }
    public void controllerShow(int time){
        videoPlayerView.controllerShow(time);
    }
    public int getTime(){
        return videoPlayerView.getTime();
    }
    public void pause(){
        videoPlayerView.pause();
    }
    public void start(){
        videoPlayerView.start();
    }
    public void setLeftOnDoubleListener(OnDoubleClickListener onDoubleListener){
        mediaControllerButtonView.setLeftButtonOnDoubleClickListener(onDoubleListener);
    }
    public void setLeftOnMoveVerticallyListener(OnMoveVerticallyListener onMoveVerticallyListener){
        mediaControllerButtonView.setLeftButtonOnMoveVerticallyListener(onMoveVerticallyListener);
    }
    public void setRightOnDoubleListener(OnDoubleClickListener onDoubleListener){
        mediaControllerButtonView.setRightButtonOnClickListener(onDoubleListener);
    }
    public void setRightOnMoveVerticallyListener(OnMoveVerticallyListener onMoveVerticallyListener){
        mediaControllerButtonView.setRightButtonOnMoveVerticallyListener(onMoveVerticallyListener);
    }
    public void setMiddleOnClickListener(OnClickListener onClickListener){
        mediaControllerButtonView.setMiddleButtonOnClickListener(onClickListener);
    }
    public void setMediaControllerOnMoveHorizontallyListener(OnMoveHorizontallyListener onMoveHorizontallyListener){
        mediaControllerButtonView.setOnMoveHorizontallyListener(onMoveHorizontallyListener);
    }
}
