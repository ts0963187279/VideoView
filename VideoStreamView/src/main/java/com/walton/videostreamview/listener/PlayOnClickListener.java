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
package com.walton.videostreamview.listener;

import android.view.View;

import com.walton.videostreamview.view.MediaControllerButtonView;

/**
 * Created by waltonmis on 2017/8/15.
 */

public class PlayOnClickListener implements View.OnClickListener {
    static boolean isPause = false;
    @Override
    public void onClick(View view) {
        MediaControllerButtonView mediaControllerButtonView = (MediaControllerButtonView)view;
        if(!isPause) {
            mediaControllerButtonView.pause();
            mediaControllerButtonView.controllerShow(1000000);
            isPause = true;
        }else {
            mediaControllerButtonView.start();
            mediaControllerButtonView.controllerShow(3000);
            isPause = false;
        }
    }
}
