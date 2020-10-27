package com.khotdee168.survey.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khotdee168.common.dao.AbstractCommonJdbcDao;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.common.utils.UserLoginUtils;
import com.khotdee168.survey.model.AnswerBean;
import com.khotdee168.survey.model.QuestionBean;
import com.khotdee168.survey.model.QuestionGroupBean;
import com.khotdee168.survey.model.QuestionSetBean;

@Repository
public class QuestionDao extends AbstractCommonJdbcDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Long insertQuestionSet(QuestionSetBean bean) {
		final String sql = "INSERT INTO tb_question_set ("
				+ "name "
				+ ",description"
				+ ",pre_flg"
				+ ",default_flg" 
				+ ",create_by, create_date) "
				+ " VALUES (trim(?), trim(?), ?, ? , ? , NOW())";
				return executeInsert(sql,
						bean.getName(), 
						bean.getDescription(), 
						bean.getPre_flg(),
						bean.getDefault_flg(),
						UserLoginUtils.getUserName() );
	}
	
 
//	public int count() {
//		return jdbcTemplate.queryForObject("select count(*) from tb_question_set where status = 1 ", Integer.class);
//	}

	@Transactional
	public boolean delete(String id) {
		
		jdbcTemplate.update("update tb_question_set set status = 0 where  id = ? ", id);
		
//		jdbcTemplate.update("delete from tb_answer where set_id = ? ", id);
//		jdbcTemplate.update("delete from tb_question where set_id = ? ", id);
//		jdbcTemplate.update("delete from tb_question_set where id = ? ", id);
		return true;
	}


	public DataTableAjax  getDataTable(QuestionSetBean param) {
		// logger.info("### getDataTable :xxxxxx ");
		DataTableAjax  result = new DataTableAjax();
		List wh = new ArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select *  from tb_question_set where status = 1 ");
		
//		 if(StringUtils.isNotEmpty(param.getPhase())){
//			wh.add("%"+param.getPhase()+"%");
//			wh.add("%"+param.getPhase()+"%");
//			sql.append(" AND ( phase like ? ");
//			sql.append(" OR number like ? ) ");
//		}
//		
		if(StringUtils.isNotEmpty(param.getName())){
			wh.add("%"+param.getName()+"%");
			sql.append("  and name like ? ");
		}
		
		if(StringUtils.isNotEmpty(param.getDescription())){
			wh.add("%"+param.getDescription()+"%");
			sql.append("  and description like ? ");
		}
		
		if(null != param.getPre_flg()){
			wh.add(param.getPre_flg());
			sql.append(" AND  pre_flg =  ? ");
		}
		 
		sql.append(" ORDER BY create_date  ");
		

//		System.out.println(sql.toString());

		List<QuestionSetBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(QuestionSetBean.class));
		int total = list != null ? list.size() : 0;
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(list);
		return result;
	}

//	public boolean checkDuplicate(CarTrackingBean bean) {
//		return jdbcTemplate.queryForObject("select count(1) from tb_test_car where  vin_no = ?  ",Integer.class, bean.getVin_no()) > 0;
//	}


	public Long insertQuestionItem(QuestionBean bean, Long sid) {
		final String sql = "INSERT INTO tb_question ("
				+ "	set_id         "
				+ "	,question_group   "
				+ "	,question_type    "
				+ "	,question_name_en "
				
				+ "	,question_name_th "
				+ "	,required_flg     "
				+ "	,scale_end        "
				+ "	,scale_end_en     "
				
				+ "	,scale_end_th     "
				+ "	,scale_start      "
				+ "	,scale_start_en    "
				 +" ,  scale_start_th    "
				
				 +" ,  create_by         "
				 +" ,  create_date       "
				+ " ) VALUES (?,?,?,? ,?,?,?,?, ?,?,?,?, ?, NOW())";
				return executeInsert(sql,
						sid,
						bean.getQuestionGroup  (),
						bean.getQuestionType   (),
						bean.getQuestionNameEn (),
						bean.getQuestionNameTh (),
						bean.getRequiredFlg    (),
						bean.getScaleEnd       (),
						bean.getScaleEndEn     (),
						bean.getScaleEndTh     (),
						bean.getScaleStart     (),
						bean.getScaleStartEn   (),
						bean.getScaleStartTh   (),
						UserLoginUtils.getUserName() );
	}

	public Long insertAnswer(AnswerBean bean, Long sid, Long qid) {
		final String sql = "INSERT INTO tb_answer ("
				+ "	set_id         "
				+ "	,question_id         "
				+ "	,answer_name_en "
				+ "	,answer_name_th "
				 +" ,  create_by         "
				 +" ,  create_date       "
				+ " ) VALUES (?,?,?,?,? ,NOW())";
				return executeInsert(sql,
						sid,
						qid,
						bean.getAnswerNameEn(),
						bean.getAnswerNameTh(), 
						UserLoginUtils.getUserName() );
		
	}

	public boolean resetDefaultFlg() {
	   return jdbcTemplate.update("update  tb_question_set set default_flg = 0 ") > 0;
	}

	public QuestionSetBean getQuestionSet(String sid, boolean sortGroup) {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_question_set where id = ? ");
		wh.add(sid);
//		QuestionSetBean bean =  jdbcTemplate.query(sql.toString(), QuestionSetBean.class , sid);
		List<QuestionSetBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(QuestionSetBean.class));

		if(null!=list && list.size()>0){
			
			QuestionSetBean bean =  list.get(0);
			
			setQuestionList(bean,sortGroup);
			
			return bean;
		}else{
//			QuestionSetBean bean =  new QuestionSetBean();
//			List<QuestionBean> qlist = new ArrayList<QuestionBean>();
//			bean.setQuestions(qlist);
			return new QuestionSetBean();
		}
		
	}
	
	public void setQuestionList(QuestionSetBean bean, boolean sortGroup) {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_question where set_id = ? ");
		
		if(sortGroup){
			sql.append(" order by question_group ASC");
		}else{
			sql.append(" order by question_id ASC ");
		}
		
		
		wh.add(bean.getId());
		List<QuestionBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(QuestionBean.class));
		for (QuestionBean questionBean : list) {
			setAnswerist(questionBean);
		}
		bean.setQuestions(list);
	}
	
	public void setAnswerist(QuestionBean bean) {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_answer where question_id = ? ");
		wh.add(bean.getQuestionId());
		List<AnswerBean> answers = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(AnswerBean.class));
		bean.setAnswers(answers);
	}

	public int updateQuestionSet(QuestionSetBean bean) {
		
		jdbcTemplate.update("delete from tb_answer where set_id = ? ", bean.getId());
		jdbcTemplate.update("delete from tb_question where set_id = ? ", bean.getId());
		
		final String sql = "update  tb_question_set set name = ? "
				+ ",description =? "
				+ ",pre_flg  =?"
				+ ",default_flg  =?" 
				+ ",update_by =?, update_date = NOW() "
				+ " where  id = ? ";
				return executeUpdate(sql,
						bean.getName(), 
						bean.getDescription(), 
						bean.getPre_flg(),
						bean.getDefault_flg(),
						UserLoginUtils.getUserName() ,
						bean.getId()
						);
		
	}


	public QuestionSetBean getQuestionView(String sid) {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_question_set where id = ? ");
		wh.add(sid);
//		QuestionSetBean bean =  jdbcTemplate.query(sql.toString(), QuestionSetBean.class , sid);
		List<QuestionSetBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(QuestionSetBean.class));

		if(null!=list && list.size()>0){
			
			QuestionSetBean bean =  list.get(0);
			
			setQuestionGroupList(bean);
			
			return bean;
		}else{
//			QuestionSetBean bean =  new QuestionSetBean();
//			List<QuestionBean> qlist = new ArrayList<QuestionBean>();
//			bean.setQuestions(qlist);
			return new QuestionSetBean();
		}
	}


	private void setQuestionGroupList(QuestionSetBean bean) {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_question_group ");
		sql.append(" order by id ASC ");
		List<QuestionGroupBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(QuestionGroupBean.class));
		for (QuestionGroupBean gbean : list) {
			setQuestionListByGroup(gbean, bean.getId());
		}
		
		bean.setQuestionGroups(list);
		
	}


	private void setQuestionListByGroup(QuestionGroupBean gbean, Long sid) {

			List wh = new ArrayList();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from tb_question where set_id = ? and question_group = ? ");
			sql.append(" order by question_id ASC ");
		
			wh.add(sid);
			wh.add(gbean.getId());
			List<QuestionBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(QuestionBean.class));
			for (QuestionBean questionBean : list) {
				setAnswerist(questionBean);
			}
			gbean.setQuestions(list);
	}


	 
	 
}
