package edu.ar.bd2.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ar.bd2.api.dto.ApprovalRuleDTO;
import edu.ar.bd2.entity.ApprovalRule;
import edu.ar.bd2.mapper.ApprovalRuleMapper;

@Service
public class ApprovalRuleDTOService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalRuleDTOService.class);

	@Autowired
	private ApprovalRuleService internalService;

	@Autowired
	private ApprovalRuleMapper mapper;

	public ApprovalRuleDTO create(ApprovalRuleDTO approvalRuleDTO) {

		ApprovalRule approvalRule = mapper.approvalRuleDTOToApprovalRule(approvalRuleDTO);

		ApprovalRule savedApprovalRule = internalService.create(approvalRule);

		return mapper.approvalRuleToApprovalRuleDTO(savedApprovalRule);
	}

	public ApprovalRuleDTO update(ApprovalRuleDTO approvalRuleDTO) {

		ApprovalRule approvalRule = mapper.approvalRuleDTOToApprovalRule(approvalRuleDTO);

		return mapper.approvalRuleToApprovalRuleDTO(internalService.update(approvalRule));

	}

	public ApprovalRuleDTO delete(String id) {

		ApprovalRule approvalRule = internalService.getById(UUID.fromString(id));

		internalService.delete(UUID.fromString(id));

		return mapper.approvalRuleToApprovalRuleDTO(approvalRule);
	}

	public ApprovalRuleDTO getById(String id) {
		ApprovalRule approvalRule = internalService.getById(UUID.fromString(id));

		return mapper.approvalRuleToApprovalRuleDTO(approvalRule);
	}

	public ApprovalRuleDTO getByIdAndVersion(String id, long version) {
		ApprovalRule approvalRule = internalService.getByIdAndVersion(UUID.fromString(id), version);

		return mapper.approvalRuleToApprovalRuleDTO(approvalRule);
	}

	public List<ApprovalRuleDTO> getByIds(List<String> ids) {

		List<ApprovalRule> approvalRules = internalService
				.getByIds(ids.stream().map(UUID::fromString).collect(Collectors.toList()));

		return approvalRules.stream().map(mapper::approvalRuleToApprovalRuleDTO).collect(Collectors.toList());
	}

	public List<ApprovalRuleDTO> getAll() {
		List<ApprovalRule> approvalRules = internalService.getAll();

		return approvalRules.stream().map(mapper::approvalRuleToApprovalRuleDTO).collect(Collectors.toList());
	}

}
