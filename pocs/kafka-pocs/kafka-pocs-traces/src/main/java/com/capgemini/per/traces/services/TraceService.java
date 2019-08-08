package com.capgemini.per.traces.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.per.models.Trace;
import com.capgemini.per.traces.models.TraceDocument;
import com.capgemini.per.traces.repository.TraceRepository;

@Service
public class TraceService {
	
	@Autowired
	private TraceRepository repository;
	
	public void save(Trace trace) {
		TraceDocument document = new TraceDocument();
		document.setTrace(trace);
		document.setId(trace.getIdTrace().toString());
		repository.save(document);
	}
}
