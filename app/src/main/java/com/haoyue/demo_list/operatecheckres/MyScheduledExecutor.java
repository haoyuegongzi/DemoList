package com.haoyue.demo_list.operatecheckres;

import com.haoyue.demo_list.thread.EventThreadMsg;

import de.greenrobot.event.EventBus;

/**
 * Created by chen1 on 2017/12/15.
 * TO DO:
 */

public class MyScheduledExecutor implements Runnable {

    private String flag;

    public MyScheduledExecutor(String flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        EventBus.getDefault().post(new EventCheckerMsg(flag));
    }
}
