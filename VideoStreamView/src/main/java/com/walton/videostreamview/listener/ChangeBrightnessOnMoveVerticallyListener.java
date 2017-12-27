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
