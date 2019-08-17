package edu.ar.bd2.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ar.bd2.api.dto.ApprovalRuleDTO;
import edu.ar.bd2.service.ApprovalRuleDTOService;

@CrossOrigin
@RestController
@ResponseBody
public class ApprovalRuleController {

	@Autowired
	private ApprovalRuleDTOService defaultService;

	@PostMapping(value = "/approval_rule")
	public ApprovalRuleDTO createApprovalGuidelines(@Valid @RequestBody ApprovalRuleDTO approvalRuleDTO,
			HttpServletRequest request) {

		return defaultService.create(approvalRuleDTO);
	}

	@PatchMapping(value = "/approval_rule")
	public ApprovalRuleDTO updateApprovalGuidelines(@Valid @RequestBody ApprovalRuleDTO approvalRuleDTO,
			HttpServletRequest request) {

		return defaultService.update(approvalRuleDTO);
	}

	@DeleteMapping(value = "/approval_rule/{id}")
	public ApprovalRuleDTO deleteApprovalRule(@NotBlank @PathVariable String id,
			HttpServletRequest request) {

		return defaultService.delete(id);
	}

	@GetMapping(value = "/approval_rule/{id}")
	public ApprovalRuleDTO getApprovalRule(@NotBlank @PathVariable String id,
			HttpServletRequest request) {

		return defaultService.getById(id);
	}

	@GetMapping(value = "/approval_rule")
	public List<ApprovalRuleDTO> getAllApprovalRules(HttpServletRequest request) {

		return defaultService.getAll().stream().sorted(Comparator.comparingInt(ApprovalRuleDTO::getYear).reversed())
				.collect(Collectors.toList());
	}
}
