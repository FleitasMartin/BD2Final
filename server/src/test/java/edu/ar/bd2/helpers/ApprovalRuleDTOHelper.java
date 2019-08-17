package edu.ar.bd2.helpers;

import java.util.List;

import com.datastax.oss.driver.shaded.guava.common.collect.Lists;

import edu.ar.bd2.api.dto.ApprovalRuleDTO;
import edu.ar.bd2.api.dto.EvaluationDTO;

public class ApprovalRuleDTOHelper {

	public static ApprovalRuleDTO defaultApprovalRuleDTO() {
		ApprovalRuleDTO approvalRuleDTO = new ApprovalRuleDTO();
		approvalRuleDTO.setMatterCode("MAT04");
		approvalRuleDTO.setMatterName("Matemática 4");
		approvalRuleDTO.setYear(2019);
		approvalRuleDTO.setSeason("Primer semestre");
		List<EvaluationDTO> evaluations = Lists.newArrayList(EvaluationDTOHelper.defaultEvaluationDTO());
		approvalRuleDTO.setEvaluations(evaluations);
		return approvalRuleDTO;
	}
}
