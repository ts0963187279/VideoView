package com.example.mylibrary.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.mylibrary.view.MediaControllerView;

/**
 * Created by waltonmis on 2017/8/21.
 */

public class LeftGestureListener extends GestureDetector.SimpleOnGestureListener{
    View.OnClickListener onClickListener = null;
    View.OnClickListener onDoubleClickListener = new RewindOnDoubleClickListener();
    View.OnLongClickListener onLongClickListener = null;
    MediaControllerView mediaControllerView = null;
    public void setLongClickeListener(View.OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public void setOnDoubleClickListener(View.OnClickListener onDoubleClickListener){
        this.onDoubleClickListener = onDoubleClickListener;
    }
    public void setView(View view){
        mediaControllerView = (MediaControllerView)view;
    }
    @Override
    public boolean onDoubleTap(MotionEvent event){
        if(onDoubleClickListener != null) {
            onDoubleClickListener.onClick(mediaControllerView);
        }
        return true;
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent event){
        if(onClickListener != null){
            onClickListener.onClick(mediaControllerView);
        }
        return true;
    }
    @Override
    public void onLongPress(MotionEvent event){
        if(onClickListener != null){
            onLongClickListener.onLongClick(mediaControllerView);
        }
    }
    @Override
    public boolean onDown(MotionEvent event){
        return true;
    }
}
