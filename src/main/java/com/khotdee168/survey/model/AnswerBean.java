package com.khotdee168.survey.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnswerBean {

	private String id;
	private String questionId;
	private String setId;
	private String answerNameEn;
	private String answerNameTh;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getSetId() {
		return setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public String getAnswerNameEn() {
		return answerNameEn;
	}

	public void setAnswerNameEn(String answerNameEn) {
		this.answerNameEn = answerNameEn;
	}

	public String getAnswerNameTh() {
		return answerNameTh;
	}

	public void setAnswerNameTh(String answerNameTh) {
		this.answerNameTh = answerNameTh;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}