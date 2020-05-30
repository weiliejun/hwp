package com.hwp.admin.components.message;


/**
 * @author 张可乐
 * @version 1.0
 * @description 消息队列接口
 * @update 2017-10-26 下午3:52:41
 */
public interface MQService {
    /**
     * @param queueName
     * @param content
     * @description 小队列中存放字符串内容
     * @version 1.0
     * @author 张可乐
     * @update 2017-10-26 下午3:55:03
     */
    void sendStringMessage(String queueName, String content);

    /**
     * @param queueName
     * @return
     * @description 从队列中取出字符串内容
     * @version 1.0
     * @author 张可乐
     * @update 2017-10-26 下午3:55:33
     */
    String getStringMessage(String queueName);

    /**
     * @param channel
     * @param message
     * @description 发布消息
     * @version 1.0
     * @author 张可乐
     * @update 2017-10-27 下午1:16:10
     */
    void publish(String channel, String message);
}
