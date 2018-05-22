package com.haoyue.demo_list.async;

import android.os.AsyncTask;

/**
 * Created by chen1 on 2017/7/14.
 */

public class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {

    @Override
    protected Boolean doInBackground(Void... voids) {//后台执行，不能进行UI操作
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {//可以更新UI
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {//显示UI
        super.onPreExecute();
    }
}
