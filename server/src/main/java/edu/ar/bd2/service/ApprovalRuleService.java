package edu.ar.bd2.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Service;

import edu.ar.bd2.api.exception.NotFoundException;
import edu.ar.bd2.dao.ApprovalRuleRepository;
import edu.ar.bd2.entity.ApprovalRule;

@Service
public class ApprovalRuleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalRuleDTOService.class);

	@Autowired
	private ApprovalRuleRepository repo;

	@Autowired
	private CassandraTemplate cassandraTemplate;

	public ApprovalRule create(ApprovalRule approvalRule) {
		return repo.save(approvalRule);
	}

	public ApprovalRule update(ApprovalRule approvalRule) {

		return cassandraTemplate.update(approvalRule);
	}

	public void delete(UUID id) {
		repo.deleteById(id);
	}

	public ApprovalRule getById(UUID id) {

		return repo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("ApprovalRule with id [%s] not found", id)));

	}

	public ApprovalRule getByIdAndVersion(UUID id, long version) {

		return repo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("ApprovalRule with id [%s] not found", id)));

	}

	public List<ApprovalRule> getByIds(List<UUID> ids) {

		return repo
				.findAllById(ids);

	}

	public List<ApprovalRule> getAll() {

		return repo.findAll();

	}
}
