package com.walton.videostreamview.view;

import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.walton.videostreamview.listener.OnDoubleClickListener;
import com.walton.videostreamview.listener.OnMoveHorizontallyListener;
import com.walton.videostreamview.listener.OnMoveVerticallyListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by waltonmis on 2017/8/15.
 */

public class VideoStreamView extends RelativeLayout{
    private VideoPlayerView videoPlayerView;
    private MediaControllerView mediaControllerView;
    private Context context;
    private ProgressBar progressBar;
    public VideoStreamView(Context context, InputStream inputStream) {
        super(context);
        setGravity(Gravity.CENTER_HORIZONTAL);
        this.context = context;
        progressBar = new ProgressBar(context);
        addView(progressBar);
        new LoadVideo().execute(inputStream);
        mediaControllerView = new MediaControllerView(context);
    }
    public void setLeftOnDoubleListener(OnDoubleClickListener onDoubleListener){
        mediaControllerView.setLeftOnDoubleListener(onDoubleListener);
    }
    public void setLeftOnMoveVerticallyListener(OnMoveVerticallyListener onMoveVerticallyListener){
        mediaControllerView.setLeftOnMoveVerticallyListener(onMoveVerticallyListener);
    }
    public void setRightOnDoubleListener(OnDoubleClickListener onDoubleListener){
        mediaControllerView.setRightOnDoubleListener(onDoubleListener);
    }
    public void setRightOnMoveVerticallyListener(OnMoveVerticallyListener onMoveVerticalListener){
        mediaControllerView.setRightOnMoveVerticallyListener(onMoveVerticalListener);
    }
    public void setMiddleOnClickListener(OnClickListener onClickListener){
        mediaControllerView.setMiddleOnClickListener(onClickListener);
    }
    public void setMediaControllerOnMoveHorizontallyListener(OnMoveHorizontallyListener onMoveHorizontallyListener){
        mediaControllerView.setMediaControllerOnMoveHorizontallyListener(onMoveHorizontallyListener);
    }
    private class LoadVideo extends AsyncTask<InputStream, Integer, File> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
        @Override
        protected File doInBackground(InputStream... inputStreams) {
            File file = null;
            try {
                file = File.createTempFile("temp",".tmp");
                FileOutputStream out = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
                byte[] buffer = new byte[2048];
                int count;
                while((count = inputStreams[0].read(buffer)) != -1){
                    bufferedOutputStream.write(buffer,0,count);
                    bufferedOutputStream.flush();
                }
                out.flush();
                out.close();
                inputStreams[0].close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                Toast.makeText(context, "Check your network", Toast.LENGTH_SHORT).show();
            }
            return file;
        }
        @Override
        protected void onPostExecute(File file) {
            progressBar.setVisibility(GONE);
            videoPlayerView = new VideoPlayerView(context,file);
            addView(videoPlayerView);
            mediaControllerView.setVideoView(videoPlayerView);
            addView(mediaControllerView);
        }
    }
}

