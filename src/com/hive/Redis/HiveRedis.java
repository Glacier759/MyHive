package com.hive.Redis;

import com.hive.ReadConfig.Config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class HiveRedis {
	
	private String Key;
	private Jedis Redis;
	public JedisPool pool;
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
		pool = new JedisPool(new JedisPoolConfig(), host, port);
		//Redis = pool.getResource();
	}
	public void ConnectRedis( String host ) {
		pool = new JedisPool(new JedisPoolConfig(), host, 6479);
		//Redis = pool.getResource();
	}
	public void ConnectRedis() {
		pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
		//Redis = pool.getResource();
	}
	public void setKey( String Key ) {
		this.Key = Key;
	}
	public String getKey() {
		return Key;
	}
	public void pushValue( String Value ) {
		Redis = pool.getResource();
		Redis.lpush(Key, Value);
		pool.returnResource(Redis);
		//Redis.lpush(Key, Value);
	}
	public String popValue() {
		Redis = pool.getResource();
		String Value = Redis.rpoplpush(Key, "Temp");
		pool.returnResource(Redis);
		return Value;
		//return Redis.rpoplpush(Key, "Temp");
	}
	public int getLength() {
		Redis = pool.getResource();
		Long longLength = Redis.llen(Key);
		int length = new Integer(String.valueOf(longLength));
		pool.returnResource(Redis);
		return length;
		//return Redis.llen(Key).intValue();
	}
	public void delKey() {
		Redis = pool.getResource();
		Redis.del(Key);
		pool.returnResource(Redis);
		pool.destroy();
	}
	public void clearRedis() {
		Redis = pool.getResource();
		Redis.flushAll();
		pool.returnResource(Redis);
		pool.destroy();
	}
}