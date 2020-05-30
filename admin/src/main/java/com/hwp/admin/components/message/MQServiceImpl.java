package com.hwp.admin.components.message;

import com.hwp.common.util.StringHelper;
import com.hwp.common.web.base.AbstractServiceParent;
import org.springframework.stereotype.Service;


@Service("mQService")
public class MQServiceImpl extends AbstractServiceParent implements MQService {
//	@Autowired
//	RedisClient redisClient;

    @Override
    public void sendStringMessage(String queueName, String content) {
        if (StringHelper.isNotBlank(queueName) && StringHelper.isNotBlank(content)) {
//			redisClient.rpush(queueName.getBytes(), content.getBytes());
        }
    }

    @Override
    public String getStringMessage(String queueName) {
        byte[] btyes = null;
        if (StringHelper.isNotBlank(queueName)) {
//			btyes = redisClient.rpop(queueName.getBytes());
        }
        return new String(btyes);
    }

    @Override
    public void publish(String channel, String message) {

//		redisClient.publish(channel, message);
    }

}
