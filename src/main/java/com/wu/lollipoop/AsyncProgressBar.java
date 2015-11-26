package com.wu.lollipoop;

import android.os.AsyncTask;

import com.wu.UI.CustomProgress;

/**
 * Created by Administrator on 2015/4/7.
 */
public class AsyncProgressBar extends AsyncTask<Void,Integer,Void> {
    private CustomProgress progress;

    public AsyncProgressBar(CustomProgress progress) {
        this.progress = progress;
    }

    @Override
    public  Void doInBackground(Void[] Void) {
        for (int i=0;i<361;i++){
            publishProgress(i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer[] values) {
        progress.setProgress(values[0]);
    }
}
