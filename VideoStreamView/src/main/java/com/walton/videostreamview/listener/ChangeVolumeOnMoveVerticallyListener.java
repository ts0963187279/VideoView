package com.walton.videostreamview.listener;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;

/**
 * Created by waltonmis on 2017/12/18.
 */

public class ChangeVolumeOnMoveVerticallyListener extends OnMoveVerticallyListener {
    @Override
    public void onMoveVertically(View view, float yPosition) {
        AudioManager audioManager = (AudioManager)view.getContext().getSystemService(Context.AUDIO_SERVICE);
        if(yPosition - getFirstYPosition() < -50){
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE,0);
            setFirstYPosition(yPosition);
        }else if(yPosition - getFirstYPosition() > 50){
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER,0);
            setFirstYPosition(yPosition);
        }
    }
}
