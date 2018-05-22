package com.haoyue.demo_list.download;

/**
 * Created by chen1 on 2017/8/1.
 */

public interface DownLoadListener {

    void onProcess(int process);
    void onSuccess();
    void onFailed();
    void onPause();
    void onCancel();
}
