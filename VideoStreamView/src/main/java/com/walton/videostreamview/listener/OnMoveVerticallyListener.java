package com.walton.videostreamview.listener;

import android.view.View;

/**
 * Created by waltonmis on 2017/12/18.
 */

public abstract class OnMoveVerticallyListener {
    private float firstYPosition;
    public abstract void onMoveVertically(View view, float yPosition);
    public void setFirstYPosition(float firstYPosition) {
        this.firstYPosition = firstYPosition;
    }
    public float getFirstYPosition() {
        return firstYPosition;
    }
}
