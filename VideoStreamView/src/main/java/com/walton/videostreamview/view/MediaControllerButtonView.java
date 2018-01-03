/*
 * Copyright (C) 2018 RS Wong <ts0963187279@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.walton.videostreamview.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.walton.videostreamview.listener.*;
/**
 * Created by waltonmis on 2017/12/15.
 */

public class MediaControllerButtonView extends LinearLayout {
    private MediaControlButton left;
    private MediaControlButton middle;
    private MediaControlButton right;
    private boolean moveHorizontallyMod = false;
    private boolean moveVerticallyMod = false;
    private float xPosition;
    private float yPosition;
    private OnMoveHorizontallyListener onMoveHorizontallyListener = new ChangeProgressOnMoveHorizontallyListener();
    private VideoPlayerView videoPlayerView;
    public MediaControllerButtonView(Context context) {
        super(context);
        setGravity(Gravity.CENTER_HORIZONTAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setWeightSum(10);
        left = new MediaControlButton(context,this);
        middle = new MediaControlButton(context,this);
        right = new MediaControlButton(context,this);
        left.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,4.0f));
        middle.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,2.0f));
        right.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,4.0f));
        left.setBackgroundColor(Color.argb(0,0,100,0));
        middle.setBackgroundColor(Color.argb(0,100,0,0));
        right.setBackgroundColor(Color.argb(0,0,0,100));
        addView(left);
        addView(middle);
        addView(right);
        left.setOnMoveVerticallyListener(new ChangeBrightnessOnMoveVerticallyListener());
	left.setOnDoubleClickListener(new RewindOnDoubleClickListener());;
	right.setOnMoveVerticallyListener(new ChangeVolumeOnMoveVerticallyListener());
	right.setOnDoubleClickListener(new ForwardOnDoubleClickListener());
	middle.setOnClickListener(new PlayOnClickListener());
    }
    public void setVideoPlayerView(VideoPlayerView videoPlayerView){
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
    public void setRightButtonOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener){
        right.setOnDoubleClickListener(onDoubleClickListener);
    }
    public void setRightButtonOnMoveVerticallyListener(OnMoveVerticallyListener onMoveVerticallyListener){
        right.setOnMoveVerticallyListener(onMoveVerticallyListener);
    }
    public void setLeftButtonOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener){
        left.setOnDoubleClickListener(onDoubleClickListener);
    }
    public void setLeftButtonOnMoveVerticallyListener(OnMoveVerticallyListener onMoveVerticallyListener){
        left.setOnMoveVerticallyListener(onMoveVerticallyListener);
    }
    public void setMiddleButtonOnClickListener(OnClickListener onClickListener){
        middle.setOnClickListener(onClickListener);
    }
    public void setOnMoveHorizontallyListener(OnMoveHorizontallyListener onMoveHorizontallyListener){
        this.onMoveHorizontallyListener = onMoveHorizontallyListener;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                xPosition = event.getX();
                yPosition = event.getY();
                onMoveHorizontallyListener.setFirstXPosition(xPosition);
                break;
            case MotionEvent.ACTION_MOVE :
                if(Math.abs(event.getY() - yPosition) > 50 && moveHorizontallyMod != true)
                    moveVerticallyMod = true;
                if(Math.abs(event.getX() - xPosition) > 50 && moveVerticallyMod != true)
                    moveHorizontallyMod = true;
                if(moveHorizontallyMod) {
                    onMoveHorizontallyListener.onMoveHorizontally(this,event.getX());
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP :
                moveVerticallyMod = false;
                moveHorizontallyMod = false;
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
