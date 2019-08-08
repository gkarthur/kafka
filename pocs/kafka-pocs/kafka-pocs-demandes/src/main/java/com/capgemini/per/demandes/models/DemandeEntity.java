package com.capgemini.per.demandes.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "poc_demande")
public class DemandeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String numeroDemande;
	@Column
	private String numeroClient;
	@Column
	private String statut;
	
	public DemandeEntity() {
		super();
	}
	
	public DemandeEntity(String numeroDemande, String numeroClient, String statut) {
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
