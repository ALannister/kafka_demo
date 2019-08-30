package com.lannister.java.demo.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class NewProducer {
	public static void main(String[] args) {
		Properties properties = new Properties();
		//kafka����˵��������Ͷ˿ں�
		properties.put("bootstrap.servers", "hadoop202:9092");
		//�ȴ����и����ڵ��Ӧ��
		properties.put("acks", "all");
		//��Ϣ���͵�����Դ���
		properties.put("retries", 0);
		//һ����Ϣ�����С
		properties.put("batch.size", 16384);
		//������ʱ
		properties.put("linger.ms", 1);
		//���ͻ������ڴ��С
		properties.put("buffer.memory", 33554432);
		//key���л�
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		//value���л�
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		for(int i=0;i<50;i++) {
			producer.send(new ProducerRecord<String, String>("test", String.valueOf(i), "Hello world - " + i));
		}
		producer.close();
		
	}
}
