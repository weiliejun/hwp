package com.baidu.yun.push.model;

import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.R;
import com.baidu.yun.core.annotation.RangeRestrict;
import com.baidu.yun.push.constants.BaiduPushConstants;

public class QueryTagsRequest extends PushRequest {

    @HttpParamKeyName(name = BaiduPushConstants.TAG_NAME, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 128)
    private String tagName = null;

    @HttpParamKeyName(name = BaiduPushConstants.START, param = R.OPTIONAL)
    private Integer start = new Integer(0);

    @HttpParamKeyName(name = BaiduPushConstants.LIMIT, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 100)
    private Integer limit = new Integer(100);

    public QueryTagsRequest addDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public QueryTagsRequest addExpires(Long requestTimeOut) {
        this.expires = requestTimeOut;
        return this;
    }

    public QueryTagsRequest addLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public QueryTagsRequest addStart(Integer start) {
        this.start = start;
        return this;
    }

    // add
    public QueryTagsRequest addTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    // get
    public String getTagName() {
        return tagName;
    }

    // set
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
