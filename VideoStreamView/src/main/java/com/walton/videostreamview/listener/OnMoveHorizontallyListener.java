package com.walton.videostreamview.listener;

import android.view.View;

/**
 * Created by waltonmis on 2017/12/18.
 */

public abstract class OnMoveHorizontallyListener {
    private float firstXPosition;
    public abstract void onMoveHorizontally(View view, float xPosition);
    public void setFirstXPosition(float firstXPosition){
        this.firstXPosition = firstXPosition;
    }
    public float getFirstXPosition() {
        return firstXPosition;
    }
}
