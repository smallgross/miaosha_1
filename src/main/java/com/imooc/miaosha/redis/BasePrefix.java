package com.imooc.miaosha.redis;

public  abstract class BasePrefix implements KeyPrefix {

	
	private int expireSecondes;//设置过期时间
	private String prefix;
	
	public BasePrefix(String prefix) {//0代表永不过期
		this(0, prefix);
	
	}
	public BasePrefix(int expireSecondes,String prefix) {
		this.expireSecondes=expireSecondes;
		this.prefix=prefix;
	}
	@Override
	public int expireSecondes() {//默认0代表永远不过期
		
		return expireSecondes;
	}

	@Override
	public String getPrefix() {
		String className=getClass().getSimpleName();
		return className+":"+prefix;
	}

}
