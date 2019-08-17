package edu.ar.bd2.entity;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType
public class Evaluation extends AbstractIdentifiablePersistentObject {

	@Column
	private String date;

	@Column
	private String gradeType;

	@Column
	private String approvalScore;

	@Column
	private Boolean promotable;

	@Column
	private String approvalPromoScore;

	@Column
	private Boolean recoveryPromotable;

	@Column
	private Integer recoveryDates;

	private List<Topic> topics;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
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
		return recoveryDates;
	}

	public void setRecoveryChances(Integer recoveryChances) {
		this.recoveryDates = recoveryChances;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}



}
