package com.capgemini.per.traces.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;

import com.capgemini.per.models.Trace;

@Document(indexName = "trace", type = "trace")
public class TraceDocument {

	@Id
	private String id;
	
	@Field(type = Nested, includeInParent = true)
	private Trace trace;
	
	@Field(type = Keyword)
    private String[] tags;
	
	public TraceDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Trace getTrace() {
		return trace;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	
}
