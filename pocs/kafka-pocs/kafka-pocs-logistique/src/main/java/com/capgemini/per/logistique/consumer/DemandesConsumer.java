package com.capgemini.per.logistique.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.capgemini.per.logistique.services.LogistiqueService;
import com.capgemini.per.models.Demande;

@Component
public class DemandesConsumer {

	final static Logger LOGGER = LoggerFactory.getLogger(DemandesConsumer.class);

	@Autowired
	private LogistiqueService logistiqueService;
	
	@KafkaListener(topics = "demandes-out")
	public void processMessage(Demande demande) {
		LOGGER.info("Reception de la demande pour la logistique : {}", demande);			
		logistiqueService.process(demande);		 
	}
}
