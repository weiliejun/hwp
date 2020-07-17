package com.baidu.yun.push.model;

import com.baidu.yun.core.annotation.JSonPath;
import com.baidu.yun.core.annotation.RangeRestrict;

public class TimerResultInfo {

    @JSonPath(path = "timer_id")
    private String timerId = null;

    @JSonPath(path = "msg")
    private String message = null;

    @JSonPath(path = "send_time")
    private long sendTime;

    @JSonPath(path = "msg_type")
    @RangeRestrict(minLength = 0, maxLength = 1)
    private int msgType;

    @JSonPath(path = "range_type")
    @RangeRestrict(minLength = 0, maxLength = 7)
    private int rangeType;

    public String getMessage() {
        return message;
    }

    public int getMsgType() {
        return msgType;
    }

    public int getRangeType() {
        return rangeType;
    }

    public long getSendTime() {
        return sendTime;
    }

    // get
    public String getTimerId() {
        return timerId;
    }
}
