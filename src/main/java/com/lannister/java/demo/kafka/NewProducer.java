package com.lannister.java.demo.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class NewProducer {
	public static void main(String[] args) {
		Properties properties = new Properties();
		//kafka服务端的主机名和端口号
		properties.put("bootstrap.servers", "hadoop202:9092");
		//等待所有副本节点的应答
		properties.put("acks", "all");
		//消息发送的最大尝试次数
		properties.put("retries", 0);
		//一批消息处理大小
		properties.put("batch.size", 16384);
		//请求延时
		properties.put("linger.ms", 1);
		//发送缓冲区内存大小
		properties.put("buffer.memory", 33554432);
		//key序列化
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		//value序列化
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		for(int i=0;i<50;i++) {
			producer.send(new ProducerRecord<String, String>("test", String.valueOf(i), "Hello world - " + i));
		}
		producer.close();
		
	}
}
