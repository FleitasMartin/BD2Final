package edu.ar.bd2.entity;

import java.util.UUID;

import org.springframework.data.annotation.Version;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public abstract class AbstractIdentifiablePersistentObject {

	@PrimaryKey
	private UUID id;

	@Version
	private long version;


	public UUID getId() {
		return id;
	}

	public void setId(UUID oid) {
		this.id = oid;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
