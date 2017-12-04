package com.walton.videostreamview.view;

import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

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
    public void setLeftOnClickListener(OnClickListener onClickListener){
        mediaControllerView.setLeftOnClickListener(onClickListener);
    }
    public void setLeftOnDoubleListener(OnClickListener onDoubleListener){
        mediaControllerView.setLeftOnDoubleListener(onDoubleListener);
    }
    public void setLeftOnLongClickListener(OnLongClickListener onLongClickListener){
        mediaControllerView.setLeftOnLongClickListener(onLongClickListener);
    }
    public void setRightOnClickListener(OnClickListener onClickListener){
        mediaControllerView.setRightOnClickListener(onClickListener);
    }
    public void setRightOnDoubleListener(OnClickListener onDoubleListener){
        mediaControllerView.setRightOnDoubleListener(onDoubleListener);
    }
    public void setRightOnLongClickListener(OnLongClickListener onLongClickListener){
        mediaControllerView.setRightOnLongClickListener(onLongClickListener);
    }
    public void setMiddleOnClickListener(OnClickListener onClickListener){
        mediaControllerView.setMiddleOnClickListener(onClickListener);
    }
    public void setMiddleOnDoubleListener(OnClickListener onDoubleListener){
        mediaControllerView.setMiddleOnDoubleListener(onDoubleListener);
    }
    public void setMiddleOnLongClickListener(OnLongClickListener onLongClickListener){
        mediaControllerView.setMiddleOnLongClickListener(onLongClickListener);
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

