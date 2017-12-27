package com.walton.example.listener;

import android.view.View;

import com.walton.videostreamview.listener.OnMoveHorizontallyListener;
import com.walton.videostreamview.view.MediaControllerView;

/**
 * Created by waltonmis on 2017/12/18.
 */

public class DefaultChangeProgressOnMoveHorizontalListener extends OnMoveHorizontallyListener {
    @Override
    public void onMoveHorizontally(View view,float xPosition) {
        ((MediaControllerView)view).controllerShow(1000);
        ((MediaControllerView)view).pause();
        ((MediaControllerView)view).setTime(((MediaControllerView)view).getTime() + (int)(xPosition - getFirstXPosition()) * 75);
        ((MediaControllerView)view).start();
        setFirstXPosition(xPosition);
    }
}
