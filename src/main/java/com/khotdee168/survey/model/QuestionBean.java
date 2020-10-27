package com.khotdee168.survey.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QuestionBean {

	private static final long serialVersionUID = 1L;

	private String questionId;
	private String questionGroup;
	private String questionType;
	private String questionNameEn;
	private String questionNameTh;
	private String requiredFlg;
	private String scaleEnd;
	private String scaleEndEn;
	private String scaleEndTh;
	private String scaleStart;
	private String scaleStartEn;
	private String scaleStartTh;

	private List<AnswerBean> answers;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionGroup() {
		return questionGroup;
	}

	public void setQuestionGroup(String questionGroup) {
		this.questionGroup = questionGroup;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionNameEn() {
		return questionNameEn;
	}

	public void setQuestionNameEn(String questionNameEn) {
		this.questionNameEn = questionNameEn;
	}

	public String getQuestionNameTh() {
		return questionNameTh;
	}

	public void setQuestionNameTh(String questionNameTh) {
		this.questionNameTh = questionNameTh;
	}

	public String getRequiredFlg() {
		return requiredFlg;
	}

	public void setRequiredFlg(String requiredFlg) {
		this.requiredFlg = requiredFlg;
	}

	public String getScaleEnd() {
		return scaleEnd;
	}

	public void setScaleEnd(String scaleEnd) {
		this.scaleEnd = scaleEnd;
	}

	public String getScaleEndEn() {
		return scaleEndEn;
	}

	public void setScaleEndEn(String scaleEndEn) {
		this.scaleEndEn = scaleEndEn;
	}

	public String getScaleEndTh() {
		return scaleEndTh;
	}

	public void setScaleEndTh(String scaleEndTh) {
		this.scaleEndTh = scaleEndTh;
	}

	public String getScaleStart() {
		return scaleStart;
	}

	public void setScaleStart(String scaleStart) {
		this.scaleStart = scaleStart;
	}

	public String getScaleStartEn() {
		return scaleStartEn;
	}

	public void setScaleStartEn(String scaleStartEn) {
		this.scaleStartEn = scaleStartEn;
	}

	public String getScaleStartTh() {
		return scaleStartTh;
	}

	public void setScaleStartTh(String scaleStartTh) {
		this.scaleStartTh = scaleStartTh;
	}

	public List<AnswerBean> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerBean> answers) {
		this.answers = answers;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}