package com.lannister.maven.demo.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class OldConsumer {

	public static void main(String[] args) {
		Properties properties = new Properties();
		
		properties.put("zookeeper.connect", "hadoop202:2181");
		properties.put("group.id", "g1");
		properties.put("zookeeper.session.timeout.ms", "500");
		properties.put("zookeeper.sync.time.ms", "250");
		properties.put("auto.commit.interval.ms", "1000");
		
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
		
		HashMap<String, Integer> topicCount = new HashMap<String, Integer>();
		//key:topic value:·ÖÇøÊý
		topicCount.put("test", 1);
		
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCount);
		
		KafkaStream<byte[],byte[]> stream = consumerMap.get("test").get(0);
		
		 ConsumerIterator<byte[],byte[]> iterator = stream.iterator();
		 
		 while(iterator.hasNext()) {
			 System.out.println(new String(iterator.next().message()));
		 }
		
		
	}
}
