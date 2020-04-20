package com.imooc.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {
	@Autowired
	JedisPool jedisPool;
	@Autowired
	RedisConfig redisConfig;
public <T>T set(String key,Class<T>clazz){
	
	//使用连接池
	Jedis jedis = null; 
	try {
		jedis=jedisPool.getResource();
		String str=jedis.get(key);
		T t=stringToBean(str);
		return t;
	}finally {
		returnToPool(jedis);
	}
}
public <T>boolean set(String key,T value){
	
	//使用连接池
	Jedis jedis = null; 
	try {
		jedis=jedisPool.getResource();
		String str=beanToString(value);
		if (str==null||str.length()<=0) {
			return false;
		}
		jedis.set(key,str);
		return true;
	}finally {
		returnToPool(jedis);
	}
}


private <T>String beanToString(T value) {
	if (value ==null) {
		return null;
	}
	return JSON.toJSONString(value);
}
//页面转化为字符串
private <T>T stringToBean(String str) {
	
	return null;
}
//不是关闭redis
private void returnToPool(Jedis jedis) {
	// TODO Auto-generated method stub
	if (jedis!=null) {
		
		jedis.close();
	}
	
}
//连接池的配置属性
@Bean
public JedisPool JdeidsPoolFactory() {
	JedisPoolConfig poolConfig = new JedisPoolConfig();
	poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
	poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
	poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
	JedisPool jp=new JedisPool(poolConfig,redisConfig.getHost(),redisConfig.getPort(),redisConfig.getTimeout()*1000,redisConfig.getPassword(),0);
	return jp;
}
}
