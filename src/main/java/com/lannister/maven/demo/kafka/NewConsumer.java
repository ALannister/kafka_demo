package com.lannister.maven.demo.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class NewConsumer {
	public static void main(String[] args) {
		Properties properties = new Properties();
		
		//����kafka����ĵ�ַ������Ҫ������broker��ָ����
		properties.put("bootstrap.servers", "hadoop202:9092");
		//ָ����������
		properties.put("group.id", "test");
		//ָ���Ƿ��Զ���zookeeper�ύoffset
		properties.put("enable.auto.commit", "true");
		//�Զ��ύoffset��ʱ����
		properties.put("auto.commit.interval.ms", "1000");
		//key�����л���
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		//value�����л���
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
