package com.khotdee168.survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.survey.dao.QuestionDao;
import com.khotdee168.survey.model.AnswerBean;
import com.khotdee168.survey.model.QuestionBean;
import com.khotdee168.survey.model.QuestionSetBean;

@Service
public class QuestionService {
	
	@Autowired
	public QuestionDao dao;
	
	public DataTableAjax getDataTable(QuestionSetBean bean) {
		return dao.getDataTable(bean);
	}
	
	@Transactional
	public boolean delete(String id) {
		return dao.delete(id);
	}

	public QuestionSetBean getQuestionSet(String sid, boolean sortGroup) {
		return dao.getQuestionSet(sid,sortGroup);
	}

	@Transactional
	public boolean saveOrUpdate(QuestionSetBean bean) {
		if(null!=bean && null!=bean.getId()){
			return updateQuestionSet(bean);
		}else{
			return saveQuestionSet(bean);
		}
	}
	 

	@Transactional
	public boolean saveQuestionSet(QuestionSetBean bean) {
		
		if(bean.getDefault_flg()==1){
			dao.resetDefaultFlg();
		}
		
		Long sid = dao.insertQuestionSet(bean);
		if(null!= bean.getQuestions()){
			for (QuestionBean qbean : bean.getQuestions()) {
				Long qid = dao.insertQuestionItem(qbean, sid);
				if(null!= qbean.getAnswers()){
					for (AnswerBean ansbean : qbean.getAnswers()) {
						dao.insertAnswer(ansbean, sid,qid);
					}
				}
			}
		}
		
		return true;		
	}
	

	@Transactional
	public boolean updateQuestionSet(QuestionSetBean bean) {
		
		if(bean.getDefault_flg()==1){
			dao.resetDefaultFlg();
		}
		
		 Long sid = bean.getId();
		
		 dao.updateQuestionSet(bean);
		if(null!= bean.getQuestions()){
			for (QuestionBean qbean : bean.getQuestions()) {
				
				Long qid = dao.insertQuestionItem(qbean, sid);
				if(null!= qbean.getAnswers()){
					for (AnswerBean ansbean : qbean.getAnswers()) {
						dao.insertAnswer(ansbean, sid,qid);
					}
				}
			}
		}
		
		return true;		
	}

	public QuestionSetBean getQuestionView(String sid ) {
		return dao.getQuestionView(sid);
	}


	
}
