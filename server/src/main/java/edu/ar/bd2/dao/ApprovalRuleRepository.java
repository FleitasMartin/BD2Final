package edu.ar.bd2.dao;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import edu.ar.bd2.entity.ApprovalRule;

@Repository
public interface ApprovalRuleRepository extends CassandraRepository<ApprovalRule, UUID> {

}
