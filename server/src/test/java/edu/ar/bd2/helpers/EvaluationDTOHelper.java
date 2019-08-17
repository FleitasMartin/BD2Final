package edu.ar.bd2.helpers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.util.Lists;

import edu.ar.bd2.api.dto.EvaluationDTO;
import edu.ar.bd2.api.dto.GradeType;
import edu.ar.bd2.api.dto.TopicDTO;

public class EvaluationDTOHelper {

	public static EvaluationDTO defaultEvaluationDTO() {
		EvaluationDTO evaluation = new EvaluationDTO();
		evaluation.setApprovalScore("C");
		evaluation.setDate(LocalDate.of(2019, 5, 15));
		evaluation.setGradeType(GradeType.LETTER);
		evaluation.setPromotable(Boolean.FALSE);
		evaluation.setRecoveryChances(2);
		List<TopicDTO> topics = Lists.newArrayList();
		topics.add(TopicDTOHelper.newTopicDTO("Números Imaginarios", new BigDecimal("0.4")));
		topics.add(TopicDTOHelper.newTopicDTO("Probabilidad", new BigDecimal("0.6")));
		evaluation.setTopics(topics);
		return evaluation;
	}

	private static class TopicDTOHelper {
		public static TopicDTO newTopicDTO(String name, BigDecimal weight) {
			TopicDTO topic = new TopicDTO();
			topic.setName(name);
			topic.setWeight(weight);
			return topic;
		}
	}
}
