package com.baidu.yun.core.callback;

import com.baidu.yun.core.event.YunHttpEvent;

import java.util.List;

public interface YunHttpObservable {

    public void addBatchHttpCallBack(List<YunHttpObserver> callbacks);

    public void addHttpCallback(YunHttpObserver callback);

    public void notifyAndCallback(YunHttpEvent event);

    public void removeCallBack(YunHttpObserver callback);

}
