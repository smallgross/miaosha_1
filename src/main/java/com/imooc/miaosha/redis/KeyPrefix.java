package com.imooc.miaosha.redis;

public interface KeyPrefix {

/**
 * 
 * @return
 */
	public int expireSecondes();
	public String getPrefix();
}
