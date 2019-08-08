package com.capgemini.per.demandes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.per.demandes.models.DemandeEntity;

@Repository
public interface DemandeRepository extends JpaRepository<DemandeEntity, Long> {
	
}
