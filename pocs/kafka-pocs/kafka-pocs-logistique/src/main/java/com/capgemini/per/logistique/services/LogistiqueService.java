package com.capgemini.per.logistique.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.capgemini.per.models.Demande;

@Service
public class LogistiqueService {
	
	final static Logger LOGGER = LoggerFactory.getLogger(LogistiqueService.class);
	
    @Autowired
    private TaskExecutor taskExecutor;
    
    @Autowired
    private ApplicationContext applicationContext;
	
    public void process(Demande demande) {
    	LogistiqueTask task = applicationContext.getBean(LogistiqueTask.class);
    	task.setDemande(demande);
        taskExecutor.execute(task);
    }	
}
