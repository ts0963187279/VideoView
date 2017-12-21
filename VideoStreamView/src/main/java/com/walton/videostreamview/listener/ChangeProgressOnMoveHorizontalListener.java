package com.walton.videostreamview.listener;

import android.view.View;

import com.walton.videostreamview.view.MediaControllerView;

/**
 * Created by waltonmis on 2017/12/18.
 */

public class ChangeProgressOnMoveHorizontalListener extends OnMoveHorizontallyListener {
    @Override
    public void onMoveHorizontally(View view,float xPosition) {
        ((MediaControllerView)view).controllerShow(1000);
        ((MediaControllerView)view).pause();
        ((MediaControllerView)view).setTime(((MediaControllerView)view).getTime() + (int)(xPosition - getFirstXPosition()) * 75);
        ((MediaControllerView)view).start();
        setFirstXPosition(xPosition);
    }
}
