package com.khotdee168.survey.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QuestionSetBean {

	private Long id;
	private String name;
	private String description;
	private Integer pre_flg;
	private Integer default_flg;
	private Integer vote_flg;
	private Integer release_flg;

	private List<QuestionBean> questions;
	
	private List<QuestionGroupBean> questionGroups;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPre_flg() {
		return pre_flg;
	}

	public void setPre_flg(Integer pre_flg) {
		this.pre_flg = pre_flg;
	}

	public Integer getDefault_flg() {
		return default_flg;
	}

	public void setDefault_flg(Integer default_flg) {
		this.default_flg = default_flg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<QuestionBean> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionBean> questions) {
		this.questions = questions;
	}

	public Integer getVote_flg() {
		return vote_flg;
	}

	public void setVote_flg(Integer vote_flg) {
		this.vote_flg = vote_flg;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public List<QuestionGroupBean> getQuestionGroups() {
		return questionGroups;
	}

	public void setQuestionGroups(List<QuestionGroupBean> questionGroups) {
		this.questionGroups = questionGroups;
	}

	public Integer getRelease_flg() {
		return release_flg;
	}

	public void setRelease_flg(Integer release_flg) {
		this.release_flg = release_flg;
	}

}