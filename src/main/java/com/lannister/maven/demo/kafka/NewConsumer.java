package com.lannister.maven.demo.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class NewConsumer {
	public static void main(String[] args) {
		Properties properties = new Properties();
		
		//定义kafka服务的地址，不需要将所有broker都指定上
		properties.put("bootstrap.servers", "hadoop202:9092");
		//指定消费者组
		properties.put("group.id", "test");
		//指定是否自动向zookeeper提交offset
		properties.put("enable.auto.commit", "true");
		//自动提交offset的时间间隔
		properties.put("auto.commit.interval.ms", "1000");
		//key的序列化类
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		//value的序列化类
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		
		consumer.subscribe(Arrays.asList("test","first"));
		
		while(true) {
			ConsumerRecords<String,String> records = consumer.poll(100);
			
			for (ConsumerRecord<String, String> record : records) {
				
				System.out.printf("topic=%s, partition=%d, offset=%d, key=%s, value=%s%n",record.topic(), record.partition(), record.offset(),record.key(),record.value());
			}
		}
		
	}
}
