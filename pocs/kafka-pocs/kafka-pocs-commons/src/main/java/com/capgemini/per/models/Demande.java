package com.capgemini.per.models;

public class Demande  {	
	private Long id;
	private String numeroDemande;
	private String numeroClient;
	private String statut;
	
	public Demande() {
		super();
	}
	
	public Demande(String numeroDemande, String numeroClient, String statut) {
		this.numeroDemande = numeroDemande;
		this.numeroClient = numeroClient;
		this.statut = statut;
	}

	public String getNumeroDemande() {
		return numeroDemande;
	}
	public void setNumeroDemande(String numeroDemande) {
		this.numeroDemande = numeroDemande;
	}
	public String getNumeroClient() {
		return numeroClient;
	}
	public void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Demande [id=" + id + ", numeroDemande=" + numeroDemande + ", numeroClient=" + numeroClient + ", statut="
				+ statut + "]";
	}	
	
}
