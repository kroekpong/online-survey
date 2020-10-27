package com.khotdee168.survey.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.khotdee168.common.dao.AbstractCommonJdbcDao;
import com.khotdee168.common.utils.UserLoginUtils;
import com.khotdee168.survey.model.ActivityBean;
import com.khotdee168.survey.model.ActivityReleaseBean;
import com.khotdee168.survey.model.ActivityVoteBean;
import com.khotdee168.survey.model.AnswerVoteBean;

@Repository
public class ActivityVoteDao extends AbstractCommonJdbcDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public Long insertActivity(ActivityBean bean) {
		final String sql = "INSERT INTO tb_activity ("
		+ "activity_name "
		+ ",description"
		+ ",set_id "
		+ ",ro_start_date"
		+ ",ro_end_date"
		+ ",repair_type"
//				+ ",repair_type_name"
		+ ",channel" 
		+ ",rule" 
		+ ",submit_no" 
		+ ",cc_flg" 
		+ ",expire_date" 
		+ ",release_date" 
		+ ",create_by, create_date) "
		+ " VALUES (trim(?), trim(?), ?, ?, ? ,? ,?, ? ,?, ?, ? ,NOW(), ? , NOW())";
		return executeInsert(sql,
			bean.getActivityName(), 
			bean.getDescription(), 
			bean.getSetId(), 
			bean.getRoStartDate(), 
			bean.getRoEndDate(), 
			bean.getRepairType(), 
//						bean.getRepairTypeName(), 
			bean.getChannel(), 
			bean.getRule(), 
			bean.getSubmitNo(), 
			bean.getCcFlg(), 
			bean.getExpireDate(), 
			UserLoginUtils.getUserName() );
}
	
	

	public Long insertReleaseData(ActivityReleaseBean releaseBean) {
		final String sql = "INSERT INTO tb_activity_release ("
		+ "	act_id         "
		+ "	,set_id         "
		+ "	,uid         "
		+ "	,sid         "
		+ "	,channel         "
		+ "	,release_flg         "
		 +" ,  create_by         "
		 +" ,  create_date       "
		+ " ) VALUES (?,?,?,?,?,?,?,NOW())";
		return executeInsert(sql,
			releaseBean.getAct_id(),
			releaseBean.getSet_id(),
			releaseBean.getUid(),
			releaseBean.getSid(), 
			releaseBean.getChannel(), 
			releaseBean.getRelease_flg(), 
			UserLoginUtils.getUserName() );

	}



	public Long submitAnswerVote(AnswerVoteBean bean) {
		final String sql = "INSERT INTO tb_activity_answer ("
				+ "	vote_id         "
				+ "	,question_id         "
				+ "	,answer_id         "
				+ "	,answer_value         "
				+ "	,question_type         "
				+ "	,question_group         "
				+ "	,required_flg         "
				 +" ,  create_by         "
				 +" ,  create_date       "
				+ " ) VALUES (?,?,?,?,?,?,?,?,NOW())";
				return executeInsert(sql,
						bean.getVoteId(),
						bean.getQuestionId() ,
						bean.getAnswerId(),
						bean.getAnswerValue(),
						bean.getQuestionType(),
						bean.getQuestionGroup(),
						bean.getRequireFlg(),
					UserLoginUtils.getUserName() ) ;
	}



	public Long submitActivityVote(ActivityVoteBean bean) {
		final String sql = "INSERT INTO tb_activity_vote ("
				+ "	act_id         "
				+ "	,set_id         "
				+ "	,uid         "
				+ "	,sid         "
				+ "	,channel         "
				+ "	,pre_flg         "
				+ "	,session_id         "
				+ "	,occupation         "
				+ "	,income         "
				+ "	,interest         "
				+ "	,expectation         "
				 +" ,  create_by         "
				 +" ,  create_date       "
				+ " ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,NOW())";
				return executeInsert(sql,
						bean.getActId(),
						bean.getSetId() ,
						bean.getUid() ,
						bean.getSid() ,
						bean.getChannel() ,
						bean.getPreFlg() ,
						bean.getSessionId() ,
						bean.getOccupation(),
						bean.getIncome() ,
						bean.getInterest() ,
						bean.getExpectation() ,
					UserLoginUtils.getUserName() );
	}



	public boolean updateCompletePreQR(ActivityVoteBean bean) {
		  return jdbcTemplate.update("update  tb_register  set vote_flg = 1 where uid = ?" , bean.getUid()) > 0;
	}


	public boolean updateCompleteFlg(ActivityVoteBean bean) {
		return jdbcTemplate.update("update  tb_activity_release  set complete_flg = 2 where sid = ?" , bean.getSid()) > 0;
	}
	 

	 
	 
}
