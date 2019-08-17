package edu.ar.bd2.mapper;

import java.util.Objects;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.ar.bd2.api.dto.ApprovalRuleDTO;
import edu.ar.bd2.api.dto.EvaluationDTO;
import edu.ar.bd2.api.dto.TopicDTO;
import edu.ar.bd2.entity.ApprovalRule;
import edu.ar.bd2.entity.Evaluation;
import edu.ar.bd2.entity.Topic;

@Mapper(componentModel = "spring", imports = { UUID.class, Objects.class })
public interface ApprovalRuleMapper {

	@Mapping(target = "id", expression = "java(approvalRule.getId().toString())")
	ApprovalRuleDTO approvalRuleToApprovalRuleDTO(ApprovalRule approvalRule);

	@Mapping(target = "id", expression = "java(Objects.nonNull(approvalRuleDTO.getId()) ? UUID.fromString(approvalRuleDTO.getId()) : UUID.randomUUID())")
	@Mapping(target = "version", expression = "java( Objects.nonNull(approvalRuleDTO.getVersion()) ? approvalRuleDTO.getVersion() : approvalRule.getVersion() )")
	ApprovalRule approvalRuleDTOToApprovalRule(ApprovalRuleDTO approvalRuleDTO);

	@Mapping(target = "id", expression = "java(evaluation.getId().toString())")
	EvaluationDTO evaluationToEvaluationDTO(Evaluation evaluation);

	@Mapping(target = "id", expression = "java(Objects.nonNull(evaluationDTO.getId()) ? UUID.fromString(evaluationDTO.getId()) : UUID.randomUUID())")
	Evaluation evaluationDTOToEvaluation(EvaluationDTO evaluationDTO);

	@Mapping(target = "id", expression = "java(topic.getId().toString())")
	TopicDTO topicToTopicDTO(Topic topic);

	@Mapping(target = "id", expression = "java(Objects.nonNull(topicDTO.getId()) ? UUID.fromString(topicDTO.getId()) : UUID.randomUUID())")
	Topic topicDTOToTopic(TopicDTO topicDTO);

}
