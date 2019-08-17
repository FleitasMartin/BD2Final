package edu.ar.bd2.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.QueryOptions;

@Configuration
@EnableConfigurationProperties(CassandraProperties.class)
@EnableCassandraRepositories(basePackages = { "edu.ar.bd2.dao" })
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Autowired
	CassandraProperties cassandraProperties;

	@Bean
	@Override
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = super.cluster();
		cluster.setJmxReportingEnabled(Boolean.FALSE);
		return cluster;
	}

	@Override
	protected QueryOptions getQueryOptions() {
		QueryOptions options = new QueryOptions();
		options.setSerialConsistencyLevel(this.cassandraProperties.getSerialConsistencyLevel());
		options.setConsistencyLevel(this.cassandraProperties.getConsistencyLevel());
		return options;
	}

	@Override
	protected String getContactPoints() {
		return cassandraProperties.getContactPoints().get(0);
	}

	@Override
	protected int getPort() {
		return cassandraProperties.getPort();
	}

	@Override
	protected String getKeyspaceName() {
		return cassandraProperties.getKeyspaceName();
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.valueOf(this.cassandraProperties.getSchemaAction());
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "edu.ar.bd2.entity" };
	}

	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(this.getKeyspaceName())
				.ifNotExists().with(KeyspaceOption.DURABLE_WRITES, true).withSimpleReplication();

		return Collections.singletonList(specification);
	}

}
