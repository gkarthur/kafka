package com.capgemini.per.batch;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.capgemini.per.evenements.services.KafkaService;
import com.capgemini.per.models.Demande;
import com.capgemini.per.models.Trace;
import com.capgemini.per.models.TypeTraitement;

@SpringBootApplication
@ComponentScan("com.capgemini.per.batch, com.capgemini.per.evenements.producer, com.capgemini.per.evenements.services")
public class Application implements CommandLineRunner {
	
	@Autowired
	private KafkaService kafkaService;
	
    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
	public void run(String... args) throws Exception {
    	
    	while (true) {    		
    		System.out.println("Batch Initialisation nouvelle demande");
    		TimeUnit.SECONDS.sleep(5);
    		
    		kafkaService.addTrace(buildTraceOk("Initialisation de la commande"));
    		
    		kafkaService.addDemandeIn(generateDemande());
    		
    		kafkaService.addTrace(buildTraceOk("Commande cree et transmis pour traitement"));
    	}
    }
    
	private Demande generateDemande() {
		Demande demande = new Demande();		
		String generatedString = RandomStringUtils.randomAlphanumeric(10);	 
	    System.out.println(generatedString);
	    demande.setId(0L);
		demande.setNumeroClient("client"+generatedString);
		demande.setNumeroDemande("numDemande"+generatedString);
		demande.setStatut("new");
		return demande;
	}

	private Trace buildTraceOk(String message) {
		Trace trace = new Trace();
		trace.setMessage(message);
		trace.setTypeTraitement(TypeTraitement.BATCH);
		return trace;
	}
}