package com.walton.videostreamview.view;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.walton.videostreamview.listener.ChangeVolumeOnMoveVerticallyListener;
import com.walton.videostreamview.listener.OnDoubleClickListener;
import com.walton.videostreamview.listener.OnMoveVerticallyListener;

/**
 * Created by waltonmis on 2017/12/14.
 */

public class MediaControlButton extends android.support.v7.widget.AppCompatButton {
    private Handler buttonHandler;
    private boolean isDoubleDown = false;
    private boolean isFirstActionDown = false;
    private boolean isFirstActionUp = false;
    private boolean hasOnDoubleClickListener = false;
    private boolean hasOnMoveVerticallyListener = false;
    private boolean moveVerticallyMod = false;
    private boolean moveHorizontallyMod = false;
    private float yPosition;
    private float xPosition;
    private OnMoveVerticallyListener onMoveVerticallyListener;
    private OnClickListener onClickListener;
    private OnDoubleClickListener onDoubleClickListener;
    private View view;
    public MediaControlButton(Context context,View mediaControllerView) {
        super(context);
        this.view = mediaControllerView;
        buttonHandler = new Handler();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ClearFirstActionDownRunnable clearFirstActionDownRunnable = new ClearFirstActionDownRunnable();
        ClearFirstActionUpRunnable clearFirstActionUpRunnable = new ClearFirstActionUpRunnable();
        OnClickRunnable onClickRunnable = new OnClickRunnable();
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                yPosition = event.getY();
                xPosition = event.getX();
                if(hasOnMoveVerticallyListener)
                    onMoveVerticallyListener.setFirstYPosition(yPosition);
                buttonHandler.postDelayed(clearFirstActionDownRunnable,200);
                isFirstActionDown = true;
                return true;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(event.getX() - xPosition) > 50 && moveVerticallyMod != true)
                    moveHorizontallyMod = true;
                if(Math.abs(event.getY() - yPosition) > 50 && moveHorizontallyMod != true)
                    moveVerticallyMod = true;
                if(moveVerticallyMod && hasOnMoveVerticallyListener) {
                    onMoveVerticallyListener.onMoveVertically(view,event.getY());
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isFirstActionUp && hasOnDoubleClickListener) {
                    onDoubleClickListener.onDoubleClick(view);
                    isDoubleDown = true;
                    return true;
                }else if(isFirstActionDown && hasOnClickListeners()){
                    buttonHandler.postDelayed(onClickRunnable,300);
                }
                buttonHandler.postDelayed(clearFirstActionUpRunnable, 300);
                isFirstActionUp = true;
                moveHorizontallyMod = false;
                moveVerticallyMod = false;
                break;
        }
        return false;
    }
    public void setOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener){
        this.onDoubleClickListener = onDoubleClickListener;
        hasOnDoubleClickListener = true;
    }
    public void setOnClickListener(@Nullable OnClickListener onclickListener){
        super.setOnClickListener(onclickListener);
        this.onClickListener = onclickListener;
    }
    public void setOnMoveVerticallyListener(OnMoveVerticallyListener onMoveVerticallyListener){
        this.onMoveVerticallyListener = onMoveVerticallyListener;
        hasOnMoveVerticallyListener = true;
    }
    private class OnClickRunnable implements Runnable{
        @Override
        public void run() {
            if(!isDoubleDown) {
                onClickListener.onClick(view);
            }else{
                isDoubleDown = false;
            }
        }
    }
    private class ClearFirstActionUpRunnable implements Runnable{
        @Override
        public void run() {
            isFirstActionUp = false;
        }
    }
    private class ClearFirstActionDownRunnable implements Runnable{
        @Override
        public void run() {
            isFirstActionDown = false;
        }
    }
}
