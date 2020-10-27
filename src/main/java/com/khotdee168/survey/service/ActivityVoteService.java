package com.khotdee168.survey.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khotdee168.common.constant.CommonConstants;
import com.khotdee168.survey.dao.ActivityVoteDao;
import com.khotdee168.survey.dao.FollowUpDao;
import com.khotdee168.survey.model.ActivityVoteBean;
import com.khotdee168.survey.model.AnswerVoteBean;

@Service
public class ActivityVoteService {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	public ActivityVoteDao dao;

	@Autowired
	public FollowUpDao fdao;
	
	
	@Transactional
	public boolean submitVote(ActivityVoteBean bean) {
		
		if(CommonConstants.CHANNEL.QR.equals(bean.getChannel())){
			bean.setActId(null);
			bean.setSid(null);
			dao.updateCompletePreQR(bean);
		}else{
			dao.updateCompleteFlg(bean);
		}
		
		Long voteId = dao.submitActivityVote(bean);
		for (AnswerVoteBean ansBean : bean.getAnswers()) {
			ansBean.setVoteId(String.valueOf(voteId));
			dao.submitAnswerVote(ansBean);
		}
		
		
		
		return true;
	}
	
	
	
 
}
