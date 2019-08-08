package com.capgemini.per.demandes.controller;

import com.capgemini.per.demandes.models.DemandeEntity;
import com.capgemini.per.models.Demande;

public class DemandesHelper {

	public static Demande buildDemande(DemandeEntity demande) {
		Demande demandeMessage = new Demande();
		demandeMessage.setId(demande.getId());
		demandeMessage.setNumeroDemande(demande.getNumeroDemande());
		demandeMessage.setNumeroClient(demande.getNumeroClient());
		demandeMessage.setStatut(demande.getStatut());
		return demandeMessage;
	}
	
	public static DemandeEntity buildDemandeEntity(Demande demande) {
		DemandeEntity demandeEntity = new DemandeEntity();
		if ((null != demande.getId()) && (demande.getId() == 0L)) {
			demandeEntity.setId(null);
		}
		else {
			demandeEntity.setId(demande.getId());
		}
		demandeEntity.setNumeroDemande(demande.getNumeroDemande());
		demandeEntity.setNumeroClient(demande.getNumeroClient());
		demandeEntity.setStatut(demande.getStatut());
		return demandeEntity;
	}
}
