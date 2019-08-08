package com.capgemini.per.traces.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.capgemini.per.models.Demande;
import com.capgemini.per.models.Trace;
import com.capgemini.per.traces.services.TraceService;

@Component
public class TracesConsumer {

	final static Logger LOGGER = LoggerFactory.getLogger(TracesConsumer.class);	
	
	@Autowired
	private TraceService traceService;
	
	@KafkaListener(topics = "traces")
	public void processMessage(Trace trace) {
		LOGGER.info("Reception Trace content : {}", trace);
		traceService.save(trace);
	}		
}
