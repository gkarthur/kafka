package com.capgemini.per.evenements.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.capgemini.per.models.Demande;
import com.capgemini.per.models.Trace;

@Service
public class KafkaService {

	private static Logger LOGGER = LoggerFactory.getLogger(KafkaService.class);

	@Autowired
	@Qualifier("kafkaTemplateJson")
	private KafkaTemplate<String, Object> producer;

	private final static String TOPIC_TRACE_NAME = "traces";
	private final static String TOPIC_DEMANDE_NAME_IN = "demandes-in";
	private final static String TOPIC_DEMANDE_NAME_OUT = "demandes-out";

	@Transactional
	public void addTrace(final Trace trace) {

		ListenableFuture<SendResult<String, Object>> future = producer.send(TOPIC_TRACE_NAME, trace);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onFailure(Throwable ex) {
				LOGGER.info("Envoie message trace KO");
			}

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				LOGGER.info("Envoie message trace OK");
			}
		});
	}

	@Transactional
	public void addDemandeIn(Demande demande) {

		ListenableFuture<SendResult<String, Object>> future = producer.send(TOPIC_DEMANDE_NAME_IN, demande);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onFailure(Throwable ex) {
				LOGGER.info("Envoie message demande KO");
			}

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				LOGGER.info("Envoie message demande OK");
			}
		});
	}
	
	@Transactional
	public void addDemandeOut(Demande demande) {

		ListenableFuture<SendResult<String, Object>> future = producer.send(TOPIC_DEMANDE_NAME_OUT, demande);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onFailure(Throwable ex) {
				LOGGER.info("Envoie message demande KO");
			}

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				LOGGER.info("Envoie message demande OK");
			}
		});
	}
}
