package com.walton.myvideoview.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.walton.myvideoview.listener.ForwardOnDoubleClickListener;
import com.walton.myvideoview.listener.LeftGestureListener;
import com.walton.myvideoview.listener.LeftOnTouchListener;
import com.walton.myvideoview.listener.MiddleGestureListener;
import com.walton.myvideoview.listener.MiddleOnTouchListener;
import com.walton.myvideoview.listener.PlayOnClickListener;
import com.walton.myvideoview.listener.RewindOnDoubleClickListener;
import com.walton.myvideoview.listener.RightGestureListener;
import com.walton.myvideoview.listener.RightOnTouchListener;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class MediaControllerView extends LinearLayout {
    Button middle,left,right;
    VideoPlayerView videoPlayerView;
    LeftOnTouchListener leftOnTouchListener ;
    LeftGestureListener leftGestureListener;
    RightOnTouchListener rightOnTouchListener ;
    RightGestureListener rightGestureListener;
    MiddleOnTouchListener middleOnTouchListener ;
    MiddleGestureListener middleGestureListener;
    public MediaControllerView(Context context) {
        super(context);
        leftOnTouchListener = new LeftOnTouchListener();
        leftGestureListener = new LeftGestureListener();
        leftGestureListener.setOnDoubleClickListener(new RewindOnDoubleClickListener());
        rightOnTouchListener = new RightOnTouchListener();
        rightGestureListener = new RightGestureListener();
        rightGestureListener.setOnDoubleClickListener(new ForwardOnDoubleClickListener());
        middleOnTouchListener = new MiddleOnTouchListener();
        middleGestureListener = new MiddleGestureListener();
        middleGestureListener.setOnClickListener(new PlayOnClickListener());
        setGravity(Gravity.CENTER_HORIZONTAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setWeightSum(10);
        left = new Button(context);
        middle = new Button(context);
        right = new Button(context);
        left.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,4.0f));
        middle.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,2.0f));
        right.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,4.0f));
        left.setBackgroundColor(Color.argb(0,0,100,100));
        middle.setBackgroundColor(Color.argb(0,0,0,0));
        right.setBackgroundColor(Color.argb(0,0,0,0));
        addView(left);
        addView(middle);
        addView(right);
    }
    public void setVideoView(VideoPlayerView videoPlayerView){
        this.videoPlayerView = videoPlayerView;
        leftOnTouchListener.setView(this);
        left.setOnTouchListener(leftOnTouchListener);
        rightOnTouchListener.setView(this);
        right.setOnTouchListener(rightOnTouchListener);
        middleOnTouchListener.setView(this);
        middle.setOnTouchListener(middleOnTouchListener);
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
    public void setLeftOnClickListener(OnClickListener onClickListener){
        leftGestureListener.setOnClickListener(onClickListener);
    }
    public void setLeftOnDoubleListener(OnClickListener onDoubleListener){
        leftGestureListener.setOnDoubleClickListener(onDoubleListener);
    }
    public void setLeftOnLongClickListener(OnLongClickListener onLongClickListener){
        leftGestureListener.setLongClickeListener(onLongClickListener);
    }
    public void setRightOnClickListener(OnClickListener onClickListener){
        rightGestureListener.setOnClickListener(onClickListener);
    }
    public void setRightOnDoubleListener(OnClickListener onDoubleListener){
        rightGestureListener.setOnDoubleClickListener(onDoubleListener);
    }
    public void setRightOnLongClickListener(OnLongClickListener onLongClickListener){
        rightGestureListener.setLongClickeListener(onLongClickListener);
    }
    public void setMiddleOnClickListener(OnClickListener onClickListener){
        middleGestureListener.setOnClickListener(onClickListener);
    }
    public void setMiddleOnDoubleListener(OnClickListener onDoubleListener){
        middleGestureListener.setOnDoubleClickListener(onDoubleListener);
    }
    public void setMiddleOnLongClickListener(OnLongClickListener onLongClickListener){
        middleGestureListener.setLongClickeListener(onLongClickListener);
    }
}
