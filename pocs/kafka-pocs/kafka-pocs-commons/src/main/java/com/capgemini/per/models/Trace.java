package com.capgemini.per.models;

import java.time.LocalDateTime; 
import java.time.ZoneId;
import java.util.UUID;

public class Trace {
	
	private UUID idTrace;

	private String dateDebut;
		
	private TypeTraitement typeTraitement;

	private String message;
	
	private Statut statut;
	
	private TypeTrace typeTrace;
		
	public Trace() {
		super();
		this.idTrace = UUID.randomUUID();
		this.setDateDebut(LocalDateTime.now().atZone(ZoneId.of("Europe/Paris")).toString());
		this.statut = Statut.OK;
		this.typeTrace = TypeTrace.TECHNIQUE;
	}

	public UUID getIdTrace() {
		return idTrace;
	}

	public void setIdTrace(UUID idTrace) {
		this.idTrace = idTrace;
	}
	
	public TypeTraitement getTypeTraitement() {
		return typeTraitement;
	}

	public void setTypeTraitement(TypeTraitement typeTraitement) {
		this.typeTraitement = typeTraitement;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public TypeTrace getTypeTrace() {
		return typeTrace;
	}

	public void setTypeTrace(TypeTrace typeTrace) {
		this.typeTrace = typeTrace;
	}	
}

