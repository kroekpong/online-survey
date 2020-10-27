package com.khotdee168.survey.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khotdee168.common.dao.AbstractCommonJdbcDao;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.common.utils.UserLoginUtils;
import com.khotdee168.survey.model.ActivityBean;
import com.khotdee168.survey.model.ActivityReleaseBean;
import com.khotdee168.survey.model.CustomerRoBean;
import com.khotdee168.survey.model.DataTableActivity;

@Repository
public class ActivityDao extends AbstractCommonJdbcDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${max.release.size}") 
    private int MAX_RELEASE_SIZE;

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
	
 
	public void updateActivity(ActivityBean bean) {
		jdbcTemplate.update("update  tb_activity set repair_type=?, channel=? "
				+ " ,rule=? , submit_no=? , expire_date=? where id = ? " 
				, bean.getRepairType(), bean.getChannel(),bean.getRule() 
				, bean.getSubmitNo(), bean.getExpireDate(), bean.getId());

	}
	
	public boolean updateCCFlg(String id) {
		   return jdbcTemplate.update("update  tb_activity  set cc_flg = 1 where id = ?" , id) > 0;
		}
	
//	public int count() {
//		return jdbcTemplate.queryForObject("select count(*) from tb_activity where status = 1 ", Integer.class);
//	}

	@Transactional
	public boolean delete(String id) {
		jdbcTemplate.update("update  tb_activity  set close_flg = 1, update_date = now() where  id = ? ", id);
		return true;
	}


	public DataTableAjax  getDataTable(ActivityBean param) {
		// logger.info("### getDataTable :xxxxxx ");
		DataTableAjax  result = new DataTableAjax();
		List wh = new ArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT a.* , q.name set_name  , q.description set_description FROM tb_activity a ");
		sql.append(" LEFT JOIN tb_question_set q  ON a.set_id = q.id where 1=1 ");
		
		sql.append("  and q.status = 1 ");
		
		if(StringUtils.isNotEmpty(param.getActivityName())){
			wh.add("%"+param.getActivityName()+"%");
			sql.append("  and a.activity_name like ? ");
		}
		
		if(StringUtils.isNotEmpty(param.getDescription())){
			wh.add("%"+param.getDescription()+"%");
			sql.append("  and  a.description like ? ");
		}
		
//		if(null != param.getPre_flg()){
//			wh.add(param.getPre_flg());
//			sql.append(" AND  pre_flg =  ? ");
//		}
//		 
		sql.append(" ORDER BY  a.create_date desc  ");
		

//		System.out.println(sql.toString());

		List<ActivityBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(ActivityBean.class));
		int total = list != null ? list.size() : 0;
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(list);
		return result;
	}

//	public boolean checkDuplicate(CarTrackingBean bean) {
//		return jdbcTemplate.queryForObject("select count(1) from tb_test_car where  vin_no = ?  ",Integer.class, bean.getVin_no()) > 0;
//	}

 
//	public Long insertAnswer(AnswerBean bean, Long sid, Long qid) {
//		final String sql = "INSERT INTO tb_answer ("
//				+ "	set_id         "
//				+ "	,question_id         "
//				+ "	,answer_name_en "
//				+ "	,answer_name_th "
//				 +" ,  create_by         "
//				 +" ,  create_date       "
//				+ " ) VALUES (?,?,?,?,? ,NOW())";
//				return executeInsert(sql,
//						sid,
//						qid,
//						bean.getAnswerNameEn(),
//						bean.getAnswerNameTh(), 
//						UserLoginUtils.getUserName() );
//		
//	}

	public boolean updateOpenFlg(String sid) {
	   return jdbcTemplate.update("update  tb_activity_release  set open_flg = (open_flg+1) where sid = ?" , sid) > 0;
	}
	
	public boolean updateVoteFlg(String sid) {
		return jdbcTemplate.update("update  tb_activity_release  set vote_flg = 1 where sid = ?" , sid) > 0;
	}
	
	public boolean updateExpireFlg(String sid) {
		return jdbcTemplate.update("update  tb_activity_release  set expire_flg = 1 where sid = ?" , sid) > 0;
	}

	public boolean updateExpireData(ActivityReleaseBean releaseBean) {
		return jdbcTemplate.update("update  tb_activity_release  set expire_flg = 1 where act_id = ? and uid =? and channel = ? and expire_flg = 0 " 
				, releaseBean.getAct_id(), releaseBean.getUid(), releaseBean.getChannel()) > 0;
	}

	public boolean updateReleaseActivityFlg(ActivityReleaseBean bean, String flg) {
		return jdbcTemplate.update("update  tb_activity_release set release_flg = ? where sid = ?" , flg, bean.getSid()) > 0;
	}
	public boolean updateReleaseQuestionFlg(ActivityBean bean) {
		return jdbcTemplate.update("update  tb_question_set set release_flg = 1 where id = ?" , bean.getSetId()) > 0;
	}

	
	public DataTableActivity  getCustomerRo(ActivityBean param) {
		DataTableActivity  result = new DataTableActivity();
		List wh = new ArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select r.* ,  DATE_FORMAT(r.create_date, '%Y-%m-%d') as settle_date  from tb_register r ");
//		sql.append("left join tb_question_set s on s.id = r.set_id ");
		sql.append(" where 1=1  ");
		sql.append(" and r.lock_flg = 0  ");
 
		buildClause(sql, wh, param);
		
		wh.add(param.getSubmitNo());
		sql.append("  ORDER BY RAND() LIMIT ?  ");
		 
		System.out.println(sql.toString());
		
		List<CustomerRoBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(CustomerRoBean.class));
		
		setUidList(result, list);
		
		int total = list != null ? list.size() : 0;
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(list);
		return result;
	}


	public DataTableActivity  getCustomerByPercent(ActivityBean param) {
		DataTableActivity  result = new DataTableActivity();
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
	 
		List<CustomerRoBean> totalList = new ArrayList();
		
		List<Map<String, Object>> dlimList = getDealerLimit(param);
//		System.out.println(dlimList);
	
		for (Map<String, Object> map : dlimList) {
			
			String dlrCode = String.valueOf(map.get("dealer_code"));
			String plimit =  String.valueOf(map.get("percent_uid"));
			Integer limit = Integer.parseInt(plimit);
//			System.out.println(MAX_RELEASE_SIZE+" > "+MAX_RELEASE_SIZE);
			
			if(limit>0){
				wh = new ArrayList();
				sql = new StringBuilder();
				sql.append("select r.* ,  DATE_FORMAT(r.create_date, '%Y-%m-%d') as settle_date  from tb_register r ");
				sql.append(" where 1=1  ");
				sql.append(" and r.lock_flg = 0  ");
					 
				buildClause(sql, wh, param);
			
				sql.append(" and r.dealer_code = ? ");
				sql.append("  ORDER BY RAND() LIMIT ?  ");
				wh.add(dlrCode);
				wh.add(limit);
				
				List<CustomerRoBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(CustomerRoBean.class));
				totalList.addAll(list);
			}
			
		}
		
		System.out.println("MAX_RELEASE_SIZE > "+MAX_RELEASE_SIZE);
		
		int total = totalList != null ? totalList.size() : 0;
		if(total > MAX_RELEASE_SIZE){
			result.setRecordsFiltered(total);
			result.setMsg("99");
			return result;
		}
		
		setUidList(result, totalList);
		
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(totalList);
		return result;
	}
	
	public List<Map<String, Object>> getDealerLimit(ActivityBean param) {
		
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select  r.dealer_code  ,COUNT(r.uid) total_uid , ROUND(COUNT(r.uid) * ? /100) percent_uid ");
		sql.append( " FROM tb_register r WHERE 1=1  ");
		sql.append(" and r.lock_flg = 0  ");

		wh.add(param.getSubmitNo());
		
		buildClause(sql, wh, param);
		
		sql.append(" GROUP BY  r.dealer_code ");
		
//		System.out.println(sql);
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString(), wh.toArray());
		
		return list;
	}
	
	private void buildClause(StringBuilder sql, List wh, ActivityBean param) {

		if (StringUtils.isNotEmpty(param.getRoStartDate())) {
			wh.add(param.getRoStartDate());
			sql.append(" AND  DATE(r.create_date) >=   STR_TO_DATE(?, '%Y-%m-%d')  ");
		}

		if (StringUtils.isNotEmpty(param.getRoEndDate())) {
			wh.add(param.getRoEndDate());
			sql.append(" AND  DATE(r.create_date)  <=  STR_TO_DATE(?,'%Y-%m-%d')   ");
		}
		
		if (StringUtils.isNotEmpty(param.getRepairType())) {
			sql.append(" AND r.repair_type in  ( SELECT name_th FROM tb_repair_type WHERE id IN ( "+param.getRepairType()+"))");
		}
		 
		if("1".equals(param.getPreFlg())){ 
			sql.append(" and r.vote_flg = 0 ");
		}
		
	}


	public void setUidList(DataTableActivity bean, List<CustomerRoBean> list) {
		List<String> uids = new ArrayList<>();
		for (CustomerRoBean b : list) {
			uids.add(b.getUid());
		}
		bean.setUids(uids);
	}


	public CustomerRoBean getRegisterData(String uid) {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_register where uid = ? ");
		wh.add(uid);
		List<CustomerRoBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(CustomerRoBean.class));
		if(null!=list && list.size()>0){
			return  list.get(0);
		}else{
			return new CustomerRoBean();
		}
	}
 
	
	public ActivityBean getActivity(String id) {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* ,  s.pre_flg , IF(a.channel='SMS','SMS','Call Center') channel_name   ");
		sql.append(" , s.name set_name , s.description set_description ");
		sql.append(" from tb_activity a left join tb_question_set s ");
		sql.append(" on a.set_id = s.id where a.id = ? ");
		wh.add(id);
		List<ActivityBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(ActivityBean.class));
		if(null!=list && list.size()>0){
			return  list.get(0);
		}else{
			return new ActivityBean();
		}
	}



	public DataTableActivity getReleaseDataList(ActivityBean bean) {
		DataTableActivity  result = new DataTableActivity();
		List wh = new ArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select r.* , DATE_FORMAT(r.create_date, '%Y-%m-%d') as settle_date ,a.release_flg, " );
		sql.append(" IF(a.channel='SMS','SMS','Call Center') channel , a.sid   ");
		sql.append(" from tb_activity_release a  ");
		sql.append(" left join tb_register r on r.uid = a.uid ");
		sql.append(" where a.act_id = ?   ");
		sql.append(" ORDER BY a.create_date ");
		   
		
		wh.add(bean.getId());
		
//		System.out.println(sql.toString());

		List<CustomerRoBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(CustomerRoBean.class));
		
		if(!bean.isViewMode()){
			setUidList(result, list);
		}
		
		int total = list != null ? list.size() : 0;
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(list);
		return result;
	}



	public String getSendSMS() {
		return jdbcTemplate.queryForObject("select value from tb_sys_param where type = 'SMS_SEND' ", String.class);
	}

	public String getContentSMS(String lang) {
		return jdbcTemplate.queryForObject("select desc_"+lang+" from tb_sys_param where type = 'SMS_CONTENT' ", String.class);
	}

	 
	 
}
