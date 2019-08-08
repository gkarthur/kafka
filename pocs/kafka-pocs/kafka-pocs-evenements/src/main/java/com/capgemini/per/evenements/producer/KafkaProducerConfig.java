package com.capgemini.per.evenements.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableKafka
public class KafkaProducerConfig {
	     
    @Value("${kafka.config.server}")
    private String bootstrapAddress;
    
    @Value("${kafka.config.transactionIdPrefix:Transaction-prefix}")
    private String transactionIdPrefix;
 
    @Bean("producerFactory")
    public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		DefaultKafkaProducerFactory<String, Object> producerFactory = new DefaultKafkaProducerFactory<>(configProps);
		producerFactory.setTransactionIdPrefix(transactionIdPrefix);		
		
		return producerFactory;
    } 
        
    @Bean("kafkaTemplateJson")
    public KafkaTemplate<String, Object> kafkaTemplateJson() {
        return new KafkaTemplate<>(producerFactory());
    }    
  
}

