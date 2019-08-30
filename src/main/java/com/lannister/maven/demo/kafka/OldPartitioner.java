package com.lannister.maven.demo.kafka;

import kafka.producer.Partitioner;

public class OldPartitioner implements Partitioner{
	

	public OldPartitioner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int partition(Object key, int numPartitions) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
