package com.example.mylibrary.listener;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.mylibrary.view.MediaControllerView;

/**
 * Created by waltonmis on 2017/8/21.
 */

public class RightOnTouchListener implements View.OnTouchListener {
    private GestureDetectorCompat gestureDetector;
    private RightGestureListener rightGestureListener;
    private MediaControllerView mediaControllerView;
    public void setGestureDetector(Context context , RightGestureListener rightGestureListener){
        this.rightGestureListener = rightGestureListener;
        this.gestureDetector = new GestureDetectorCompat(context,rightGestureListener);
    }
    public void setView(MediaControllerView mediaControllerView){
        this.mediaControllerView = mediaControllerView;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(gestureDetector == null){
            rightGestureListener = new RightGestureListener();
            gestureDetector = new GestureDetectorCompat(view.getContext(),rightGestureListener);
        }
        rightGestureListener.setView(mediaControllerView);
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }
}
