package com.capgemini.per.demandes.controller;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.capgemini.per.demandes.services.DemandesService;
import com.capgemini.per.models.Demande;

@Component
public class DemandesConsumer {

	final static Logger LOGGER = LoggerFactory.getLogger(DemandesConsumer.class);
	
	@Autowired
	private DemandesService demandesService;
	
	@KafkaListener(topics = "demandes-in")
	public void processMessage(Demande demande) {
		LOGGER.info("Reception de la demande : {}", demande);
		if (null != demande.getId() && (0L != demande.getId())) {
			demandesService.update(DemandesHelper.buildDemandeEntity(demande));
		}
		else {
			demandesService.save(DemandesHelper.buildDemandeEntity(demande));
		}
	}
		
}
