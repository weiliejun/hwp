package com.baidu.yun.push.model;

import com.baidu.yun.core.annotation.JSonPath;

public class TagInfo {

    @JSonPath(path = "tid")
    private String tagId;

    @JSonPath(path = "tag")
    private String tagName = null;

    @JSonPath(path = "info")
    private String info = null;

    @JSonPath(path = "type")
    private int type;

    @JSonPath(path = "create_time")
    private long createTime;

    public long getCreateTime() {
        return createTime;
    }

    public String getInfo() {
        return info;
    }

    // get
    public String getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public int getType() {
        return type;
    }
}
