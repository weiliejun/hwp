package com.hwp.admin.components.message;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author 张可乐
 * @version 1.0
 * @description 订阅Redis消息队列服务
 * @update 2017-10-26 下午4:22:32
 */
@Component
public class SubscribeRedis implements ApplicationContextAware {
    private Logger logger = Logger.getLogger(SubscribeRedis.class);
//	@Autowired
//	private RedisClient redisClient;
//	@Autowired
//	private RedisMsgPubSubListener redisMsgPubSubListener;

    /**
     * 启动服务时订阅相关Redis消息队列
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		/*//subscribe方法是阻塞的，故用一个线程启动
		new Thread(new Runnable() {
			public void run() {
				Jedis jedis = null;
		        try {
		        	
		            jedis = redisClient.getJedis();
		          //订阅redis消息发送服务
		    		jedis.subscribe(redisMsgPubSubListener, BusinessConstant.REDIS_MSG_CHANNEL);
		        } catch (Exception e) {
		            e.printStackTrace();
		            logger.error("订阅redis消息发送服务异常", e);
		        } finally {
		            if (jedis != null) {
		                jedis.close();
		            }
		        }
			}
		}).start();*/

    }


}
