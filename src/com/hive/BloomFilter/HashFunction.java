package com.hive.BloomFilter;

public class HashFunction {
	private int BloomFilter_Size, HashSeed;
	
	public HashFunction ( int BloomFilter_Size, int HashSeed ) {
		this.BloomFilter_Size = BloomFilter_Size;
		this.HashSeed = HashSeed;
	}
	public int Hash( String Value ) {
		int Result = 0;
		int len = Value.length();
		for ( int i = 0; i < len; i ++ ) {
			Result = HashSeed * Result + Value.charAt(i);
		}
		return Result & ( BloomFilter_Size - 1 );
	}
}
