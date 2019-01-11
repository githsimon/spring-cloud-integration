package com.gupao.springcloudstreamkafka.api;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/9/12 13:00
 **/
public class KafkaProducerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","192.168.126.130:9092");//kafka地址
        properties.setProperty("key.serializer",StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer(properties);
        String topic="gjmes2";
        Integer partition=0;
        Long timestamp = System.currentTimeMillis();
        String key = "gupao-key";
        String value = "gupao-value";
        ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>(topic,partition,timestamp,key,value);
        Future<RecordMetadata> future = kafkaProducer.send(producerRecord);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("OK");
    }
}
