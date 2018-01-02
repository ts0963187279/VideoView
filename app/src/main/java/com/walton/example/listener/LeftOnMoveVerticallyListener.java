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
package com.walton.example.listener;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;

import com.walton.videostreamview.listener.OnMoveVerticallyListener;

/**
 * Created by waltonmis on 2017/12/18.
 */

public class LeftOnMoveVerticallyListener extends OnMoveVerticallyListener {
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
