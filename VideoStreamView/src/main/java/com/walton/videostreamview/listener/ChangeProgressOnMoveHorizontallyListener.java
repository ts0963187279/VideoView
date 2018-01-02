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
