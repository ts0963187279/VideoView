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

import android.media.AudioManager;
import android.provider.Settings;
import android.view.View;
/** Created by waltonmis on 2017/12/18. */
public class ChangeBrightnessOnMoveVerticallyListener extends OnMoveVerticallyListener {
  public void onMoveVertically(View view, float yPosition) {
      int brightness = 0;
      int adjustBrightness = 0;
      try {
          brightness = Settings.System.getInt(view.getContext().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS);
      } catch (Settings.SettingNotFoundException e) {
          e.printStackTrace();
      }
      if(yPosition - getFirstYPosition() < -50){
          adjustBrightness = brightness - (int)(yPosition-getFirstYPosition())/5;
          if(adjustBrightness >=0 && adjustBrightness <= 255)
              Settings.System.putInt(view.getContext().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,adjustBrightness);
          setFirstYPosition(yPosition);
      }else if(yPosition - getFirstYPosition() > 50){
          adjustBrightness = brightness - (int)(yPosition-getFirstYPosition())/5;
          if(adjustBrightness >=0 && adjustBrightness <= 255)
              Settings.System.putInt(view.getContext().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,adjustBrightness);
          setFirstYPosition(yPosition);
      }
  }
}
