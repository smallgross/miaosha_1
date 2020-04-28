package com.imooc.miaosha.redis;

public class OrderKey extends BasePrefix{

	public OrderKey(int expireSecondes, String prefix) {
		super(expireSecondes, prefix);

	}

}
