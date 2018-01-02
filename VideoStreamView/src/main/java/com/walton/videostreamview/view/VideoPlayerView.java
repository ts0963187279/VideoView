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
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.EditText;
import com.walton.videostreamview.processor.Observer;
import com.walton.videostreamview.listener.*;
import java.io.File;
import android.widget.RelativeLayout;
import android.graphics.Color;

/**
 * Created by waltonmis on 2017/8/15.
 */


public class VideoPlayerView extends RelativeLayout implements Observer{
    private VideoView videoView;
    private MediaController mediaController;
    private VideoOnCompletionListener videoOnCompletionListener;
    private Context context;
    private MediaControllerButtonView mediaControllerButtonView;
    private EditText urlEditText;
    public VideoPlayerView(Context context) {
        super(context);
	this.context = context;
	setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
	videoView = new VideoView(context);
	LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
	layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
	videoView.setLayoutParams(layoutParams);
	mediaControllerButtonView = new MediaControllerButtonView(context);
	videoOnCompletionListener = new VideoOnCompletionListener();
        videoOnCompletionListener.setView(this);
        videoView.setOnCompletionListener(videoOnCompletionListener);
        mediaController = new MediaController(context);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
	mediaControllerButtonView.setVideoPlayerView(this);
	addView(videoView);
	urlEditText = new EditText(context);
	urlEditText.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
	urlEditText.setText("test");
	urlEditText.setBackgroundColor(Color.WHITE);
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
    public void setLeftOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener){
        mediaControllerButtonView.setLeftButtonOnDoubleClickListener(onDoubleClickListener);
    }
    public void setLeftOnMoveVerticallyListener(OnMoveVerticallyListener onMoveVerticallyListener){
        mediaControllerButtonView.setLeftButtonOnMoveVerticallyListener(onMoveVerticallyListener);
    }
    public void setRightOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener){
        mediaControllerButtonView.setRightButtonOnDoubleClickListener(onDoubleClickListener);
    }
    public void setRightOnMoveVerticallyListener(OnMoveVerticallyListener onMoveVerticalListener){
        mediaControllerButtonView.setRightButtonOnMoveVerticallyListener(onMoveVerticalListener);
    }
    public void setMiddleOnClickListener(OnClickListener onClickListener){
        mediaControllerButtonView.setMiddleButtonOnClickListener(onClickListener);
    }
    public void setOnMoveHorizontallyListener(OnMoveHorizontallyListener onMoveHorizontallyListener){
        mediaControllerButtonView.setOnMoveHorizontallyListener(onMoveHorizontallyListener);
    }
    public void update(File file){
	videoView.setVideoPath(file.getAbsolutePath());
	addView(mediaControllerButtonView);
	videoView.start();
	addView(urlEditText);
    }
}
