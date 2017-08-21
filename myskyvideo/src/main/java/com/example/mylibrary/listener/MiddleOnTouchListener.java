package com.example.mylibrary.listener;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.mylibrary.view.MediaControllerView;

/**
 * Created by waltonmis on 2017/8/21.
 */

public class MiddleOnTouchListener implements View.OnTouchListener {
    private GestureDetectorCompat gestureDetector;
    private MiddleGestureListener middleGestureListener;
    private MediaControllerView mediaControllerView;
    public void setGestureDetector(Context context , MiddleGestureListener middleGestureListener){
        this.middleGestureListener = middleGestureListener;
        this.gestureDetector = new GestureDetectorCompat(context,middleGestureListener);
    }
    public void setView(MediaControllerView mediaControllerView){
        this.mediaControllerView = mediaControllerView;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(gestureDetector == null){
            middleGestureListener = new MiddleGestureListener();
            gestureDetector = new GestureDetectorCompat(view.getContext(),middleGestureListener);
        }
        middleGestureListener.setView(mediaControllerView);
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }
}
