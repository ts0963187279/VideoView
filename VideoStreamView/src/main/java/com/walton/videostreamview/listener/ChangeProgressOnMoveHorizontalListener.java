package com.walton.videostreamview.listener;

import android.view.View;

import com.walton.videostreamview.view.MediaControllerButtonView;

/**
 * Created by waltonmis on 2017/12/18.
 */

public class ChangeProgressOnMoveHorizontalListener extends OnMoveHorizontallyListener {
    @Override
    public void onMoveHorizontally(View view,float xPosition) {
	MediaControllerButtonView mediaControllerButtonView = (MediaControllerButtonView)view;
        mediaControllerButtonView.controllerShow(1000);
	mediaControllerButtonView.pause();
	mediaControllerButtonView.setTime(mediaControllerButtonView.getTime() + (int)(xPosition - getFirstXPosition()) * 75);
	mediaControllerButtonView.start();
        setFirstXPosition(xPosition);
    }
}
