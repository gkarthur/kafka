package com.capgemini.per.traces.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.capgemini.per.traces.models.TraceDocument;

public interface  TraceRepository extends ElasticsearchRepository<TraceDocument, String> {

}
