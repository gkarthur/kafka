package com.capgemini.per.logistique.services;

import java.util.concurrent.TimeUnit; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.capgemini.per.evenements.services.KafkaService;
import com.capgemini.per.models.Demande;
import com.capgemini.per.models.Trace;
import com.capgemini.per.models.TypeTraitement;

@Component
@Scope("prototype")
public class LogistiqueTask implements Runnable {
    
	final static Logger LOGGER = LoggerFactory.getLogger(LogistiqueTask.class);
	
	@Autowired
	private KafkaService kafkaService;
	
	private Demande demande;
	
	@Override
    public void run() {
        LOGGER.info("Called from thread");
      try {
		LOGGER.info("Logistique : Demarrage traitement .... wait 1 min");			
		TimeUnit.SECONDS.sleep(10);
		
		kafkaService.addTrace(buildTraceOk("Traitement de la commande"));
		
		// traitement de la demande
		demande.setStatut("preparation");
		kafkaService.addDemandeIn(demande);
		
		LOGGER.info("Logistique : Demarrage expedetion .... wait 1 min");
		TimeUnit.SECONDS.sleep(10);

		kafkaService.addTrace(buildTraceOk("Expedition de la commande"));
		
		// traitement de la demande
		demande.setStatut("expedition");
		kafkaService.addDemandeIn(demande);
		
		} catch (InterruptedException e) {
		e.printStackTrace();
	    }
    }
		
	
	private Trace buildTraceOk(String message) {
		Trace trace = new Trace();
		trace.setMessage(message);
		trace.setTypeTraitement(TypeTraitement.LOGISTIQUE);
		return trace;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}
	
	
}
