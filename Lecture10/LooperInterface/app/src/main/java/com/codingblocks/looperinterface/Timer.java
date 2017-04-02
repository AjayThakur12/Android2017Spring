package com.codingblocks.looperinterface;

import android.os.AsyncTask;

/**
 * Created by championswimmer on 02/04/17.
 */
class Timer extends AsyncTask<Integer, Integer, Void> {

    public interface OnTickListener {
        void onTick(int secPassed);
    }

    private OnTickListener otl = null;

    public void setOnTickListener (OnTickListener onTickListener) {
        this.otl = onTickListener;
    }

    void runOneSec() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + 1000) {

        }
    }

    @Override
    protected Void doInBackground(Integer... params) {
        for (int i = 0; i < params[0]; i++) {
            runOneSec();
            publishProgress(i + 1);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        if (otl != null) {
            otl.onTick(values[0]);
        }
    }
}
