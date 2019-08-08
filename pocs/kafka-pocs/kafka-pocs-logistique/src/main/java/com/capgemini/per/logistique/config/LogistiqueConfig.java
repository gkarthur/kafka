package com.capgemini.per.logistique.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

@Configuration
public class LogistiqueConfig {

	@Autowired
	@Qualifier("producerFactory")
	ProducerFactory<String, Object> producerFactory;
	
    @Bean("transactionManager")
    public KafkaTransactionManager<String, Object> kafkaTransactionManager() {
        KafkaTransactionManager<String, Object> transactionManager = new KafkaTransactionManager<String, Object>(producerFactory);
        transactionManager.setNestedTransactionAllowed(true);
        //transactionManager.setTransactionSynchronization(KafkaTransactionManager.SYNCHRONIZATION_ON_ACTUAL_TRANSACTION);
        return transactionManager;
    }
}
