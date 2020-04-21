package com.imooc.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {
	@Autowired
	RedisConfig redisConfig;
	
	//连接池的配置属性
		@Bean
		public JedisPool JdeidsPoolFactory() {
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
			poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
			poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
			JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
					redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);
			return jp;
		}
}
