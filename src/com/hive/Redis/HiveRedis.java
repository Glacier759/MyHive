package com.hive.Redis;

import com.hive.ReadConfig.Config;

import redis.clients.jedis.Jedis;

public class HiveRedis {
	
	private String Key;
	private Jedis Redis;
	static String RedisIP = null;
	static int RedisPort;

	/*public static void main(String[] args) {
	HiveRedis obj = new HiveRedis();
	System.out.println(getSecount("URL"));
		obj.ConnectRedis("222.24.63.100", 9004);
		obj.setKey("URL");
		obj.pushValue("www.baidu.com");
		obj.pushValue("www.google.com");
		System.out.println(obj.getLength());
		System.out.println(obj.popValue());
		System.out.println(obj.popValue());
		System.out.println(obj.getLength());
		obj.Redis.flushAll();
	}*/
	public HiveRedis( Config config) {
		RedisIP = config.getRedisIP();
		RedisPort = config.getRedisPort();
	}
	static public int getSecount( String username ) {
		return new Jedis(RedisIP, RedisPort).llen(username).intValue();
	}
	public void ConnectRedis(String host, int port) {
		Redis = new Jedis(host, port);
	}
	public void ConnectRedis( String host ) {
		Redis = new Jedis(host, 6379);
	}
	public void ConnectRedis() {
		Redis = new Jedis("127.0.0.1", 6379);
	}
	public void setKey( String Key ) {
		this.Key = Key;
	}
	public String getKey() {
		return Key;
	}
	public void pushValue( String Value ) {
		Redis.lpush(Key, Value);
	}
	public String popValue() {
		return Redis.rpoplpush(Key, "Temp");
	}
	public int getLength() {
		return Redis.llen(Key).intValue();
	}
	public void delKey() {
		Redis.del(Key);
	}
	public void clearRedis() {
		Redis.flushAll();
	}
}