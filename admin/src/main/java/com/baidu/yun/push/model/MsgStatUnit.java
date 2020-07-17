package com.baidu.yun.push.model;

import com.baidu.yun.core.annotation.JSonPath;

public class MsgStatUnit {

    @JSonPath(path = "push")
    private int pushNum;

    @JSonPath(path = "ack")
    private int ackNum;

    @JSonPath(path = "del")
    private int delNum;

    @JSonPath(path = "click")
    private int clickNum;

    public int getAckNum() {
        return ackNum;
    }

    public int getClickNum() {
        return clickNum;
    }

    public int getDelNum() {
        return delNum;
    }

    public int getPushNum() {
        return pushNum;
    }
}
