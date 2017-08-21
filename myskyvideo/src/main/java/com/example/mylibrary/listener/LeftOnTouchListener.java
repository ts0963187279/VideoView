package com.example.mylibrary.listener;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.mylibrary.view.MediaControllerView;

/**
 * Created by waltonmis on 2017/8/21.
 */

public class LeftOnTouchListener implements View.OnTouchListener {
    private GestureDetectorCompat gestureDetector;
    private LeftGestureListener leftGestureListener;
    private MediaControllerView mediaControllerView;
    public void setGestureDetector(Context context , LeftGestureListener leftGestureListener){
        this.leftGestureListener = leftGestureListener;
        this.gestureDetector = new GestureDetectorCompat(context,leftGestureListener);
    }
    public void setView(MediaControllerView mediaControllerView){
        this.mediaControllerView = mediaControllerView;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(gestureDetector == null){
            leftGestureListener = new LeftGestureListener();
            gestureDetector = new GestureDetectorCompat(view.getContext(),leftGestureListener);
        }
        leftGestureListener.setView(mediaControllerView);
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }
}
