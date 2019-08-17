package edu.ar.bd2.api.dto;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EvaluationDTO {

	private String id;

	@NotNull
	private LocalDate date;

	@NotNull
	private GradeType gradeType;

	@NotBlank
	private String approvalScore;

	@NotNull
	private Boolean promotable;

	@Nullable
	private String approvalPromoScore;

	@Nullable
	private Boolean recoveryPromotable;

	@NotNull
	private Integer recoveryChances;

	@Nullable
	private List<@Valid TopicDTO> topics;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public GradeType getGradeType() {
		return gradeType;
	}

	public void setGradeType(GradeType gradeType) {
		this.gradeType = gradeType;
	}

	public String getApprovalScore() {
		return approvalScore;
	}

	public void setApprovalScore(String approvalScore) {
		this.approvalScore = approvalScore;
	}

	public Boolean getPromotable() {
		return promotable;
	}

	public void setPromotable(Boolean promotable) {
		this.promotable = promotable;
	}

	public String getApprovalPromoScore() {
		return approvalPromoScore;
	}

	public void setApprovalPromoScore(String approvalPromoScore) {
		this.approvalPromoScore = approvalPromoScore;
	}

	public Boolean getRecoveryPromotable() {
		return recoveryPromotable;
	}

	public void setRecoveryPromotable(Boolean recoveryPromotable) {
		this.recoveryPromotable = recoveryPromotable;
	}

	public Integer getRecoveryChances() {
		return recoveryChances;
	}

	public void setRecoveryChances(Integer recoveryChances) {
		this.recoveryChances = recoveryChances;
	}

	public List<TopicDTO> getTopics() {
		return topics;
	}

	public void setTopics(List<TopicDTO> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
