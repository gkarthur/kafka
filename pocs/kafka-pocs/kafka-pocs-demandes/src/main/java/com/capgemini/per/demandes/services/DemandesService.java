package com.capgemini.per.demandes.services;

import java.util.List; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.per.demandes.controller.DemandesHelper;
import com.capgemini.per.demandes.models.DemandeEntity;
import com.capgemini.per.demandes.repository.DemandeRepository;
import com.capgemini.per.evenements.services.KafkaService;
import com.capgemini.per.models.Trace;
import com.capgemini.per.models.TypeTraitement;

@Service
public class DemandesService {

	final static Logger LOGGER = LoggerFactory.getLogger(DemandesService.class);

	@Autowired
	private DemandeRepository demandeRepository;

	@Autowired
	private KafkaService messageService;

	public List<DemandeEntity> findAll() {
		Trace trace = buildTraceOk("Recuperation des demandes en bases");
		messageService.addTrace(trace);
		return demandeRepository.findAll();
	}

	public void save(DemandeEntity demande) {
		LOGGER.info("Enregistrement de la nouvelle demande");
		demandeRepository.save(demande);
		
		Trace trace = buildTraceOk("Creation demande");
		messageService.addTrace(trace);		
		
		messageService.addDemandeOut(DemandesHelper.buildDemande(demande));
	}

	public void update(DemandeEntity demande) {
		LOGGER.info("Update de la demande : {}", demande);
		try {
			DemandeEntity demandeExist = demandeRepository.findById(demande.getId()).get();
			if (null != demandeExist) {			
				demandeExist.setStatut(demande.getStatut());
				LOGGER.info("Demande avec nouvelles donnees : {}", demandeExist);
				demandeRepository.save(demandeExist);
				
				Trace trace = buildTraceOk("Mise à jour demande avec le statut " + demande.getStatut());
				messageService.addTrace(trace);
			}
		}
		catch (java.util.NoSuchElementException nse) {
			LOGGER.error("Erreur update de la demande");
		}
				
	}

	private Trace buildTraceOk(String message) {
		Trace trace = new Trace();
		trace.setMessage(message);
		trace.setTypeTraitement(TypeTraitement.FIND);
		return trace;
	}

	
}
