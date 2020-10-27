package com.khotdee168.survey.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.khotdee168.common.dao.AbstractCommonJdbcDao;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.common.utils.UserLoginUtils;
import com.khotdee168.survey.model.DataTableActivity;
import com.khotdee168.survey.model.FollowUpActivityBean;
import com.khotdee168.survey.model.FollowUpLogBean;
import com.khotdee168.survey.model.FollowUpReleaseBean;

@Repository
public class FollowUpDao extends AbstractCommonJdbcDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
 

	public DataTableAjax  getDataTable(FollowUpActivityBean param) {
		// logger.info("### getDataTable :xxxxxx ");
		DataTableAjax  result = new DataTableAjax();
		List wh = new ArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ,  ");
		sql.append(" (  CASE   WHEN CURDATE() > expire_date THEN 1   ");
		sql.append(" WHEN CURDATE()+3 > expire_date THEN 2   ");
		sql.append(" ELSE 0 END ) expired_flg  ");
		sql.append("  from tb_activity where 1=1 ");
		
		if(StringUtils.isNotEmpty(param.getActivityName())){
			wh.add("%"+param.getActivityName()+"%");
			sql.append("  and activity_name like ? ");
		}
		
		if(StringUtils.isNotEmpty(param.getDescription())){
			wh.add("%"+param.getDescription()+"%");
			sql.append("  and description like ? ");
		}
		
//		if(null != param.getPre_flg()){
//			wh.add(param.getPre_flg());
//		}
//		 
		sql.append(" AND  close_flg =  0 "); // Call Center Only
		sql.append(" AND  cc_flg =  1 "); // Call Center Only
		
		sql.append(" ORDER BY create_date desc  ");
		

//		System.out.println(sql.toString());

		List<FollowUpActivityBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(FollowUpActivityBean.class));
		int total = list != null ? list.size() : 0;
		
		for (FollowUpActivityBean bean : list) {
			summaryData(bean);
		}
		
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(list);
		return result;
	}
 

	public void summaryData(FollowUpActivityBean bean) {
		Integer total = jdbcTemplate.queryForObject("select count(1) from tb_activity_release where  act_id = ?  and channel = 'CC'  ",Integer.class ,bean.getId());
		Integer completeNo = jdbcTemplate.queryForObject("select count(1) from tb_activity_release where  act_id = ?  and channel = 'CC'  and  complete_flg = 2 ",Integer.class ,bean.getId());
		bean.setTotalNo(String.valueOf(total));
		bean.setCompleteNo(String.valueOf(completeNo));
	}
	
	public boolean updateOpenFlg(String sid) {
	   return jdbcTemplate.update("update  tb_activity_release  set open_flg = (open_flg+1) where sid = ?" , sid) > 0;
	}
	
	public boolean updateVoteFlg(String sid) {
		return jdbcTemplate.update("update  tb_activity_release  set vote_flg = 1 where sid = ?" , sid) > 0;
	}
	
	public boolean updateExpireFlg(String sid) {
		return jdbcTemplate.update("update  tb_activity_release  set expire_flg = 1 where sid = ?" , sid) > 0;
	}
 

	public DataTableActivity getReleaseDataList(FollowUpActivityBean bean) {
		DataTableActivity  result = new DataTableActivity();
		List wh = new ArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select r.* , DATE_FORMAT(r.create_date, '%Y-%m-%d') as settle_date ,a.release_flg, " );
		sql.append(" a.sid , a.act_id   ,a.set_id ,a.complete_flg");
		sql.append(" from tb_activity_release a  ");
		sql.append(" left join tb_register r on r.uid = a.uid ");
		sql.append(" where a.act_id = ?   ");
		sql.append(" and a.channel = 'CC' ");
		
		wh.add(bean.getId());
		
		if(StringUtils.isNotEmpty(bean.getStatus())){
			wh.add(bean.getStatus());
			sql.append(" and a.complete_flg =  ? ");
		}
		sql.append(" ORDER BY a.create_date ");
		   
		
//		System.out.println(sql.toString());

		List<FollowUpReleaseBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(FollowUpReleaseBean.class));
		
//		setUidList(result, list);
		
		int total = list != null ? list.size() : 0;
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(list);
		return result;
	}


	public FollowUpActivityBean getActivityBySid(String sid) {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.*  , r.customer , r.tel, r.dealer_name , a.uid , a.sid ,");
		sql.append(" a.complete_flg   ,  r.vote_flg , r.lock_flg  , ");
		sql.append(" (  CASE   WHEN NOW() > t.expire_date THEN 1   ELSE 0 END ) expired_flg ");
		sql.append("  FROM tb_activity_release a ");
		sql.append(" left join tb_register r on r.uid = a.uid ");
		sql.append(" left join tb_activity t on t.id = a.act_id ");
		sql.append(" where a.sid = ? and   t.close_flg = 0 ");
		
		wh.add(sid);
		
		List<FollowUpActivityBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(FollowUpActivityBean.class));
		
		if(null!=list && list.size()>0){
			return  list.get(0);
		}else{
			return new FollowUpActivityBean();
		}
	}


	public List<FollowUpLogBean> getFollowUpLog(String sid) {
		String lang = LocaleContextHolder.getLocale().getLanguage();
		
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT l.sid , DATE_FORMAT(create_date, '%Y-%m-%d %H:%i') create_date , d.name_"+lang+" reason FROM tb_follow_up_log l ");
		sql.append("LEFT JOIN tb_sys_ddl d ON d.code = l.reason AND d.type = 'DDL_REASON' where l.sid = ?   ");
		
		wh.add(sid);
		
//		System.out.println(sql.toString());
		
		return jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(FollowUpLogBean.class));
		
	}


	public boolean saveLog(FollowUpLogBean bean) {
			final String sql = "INSERT INTO tb_follow_up_log ("
			+ "	sid         "
			+ "	,reason         "
			 +" ,  create_by         "
			 +" ,  create_date       "
			+ " ) VALUES (?,?,?,NOW())";
			return jdbcTemplate.update(sql,
					bean.getSid(), 
					bean.getReason(), 
				UserLoginUtils.getUserName())>0;
	}


	public boolean closeSurvey(FollowUpLogBean bean) {
		return jdbcTemplate.update("update  tb_activity_release  set complete_flg = 1 where sid = ?" , bean.getSid()) > 0;
	}


	public FollowUpActivityBean getActivityByUid(String uid) {
		
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  r.* , fn_getPreSurvey() AS set_id FROM tb_register r   ");
		sql.append(" where r.uid = ?   ");
		
		wh.add(uid);
		
		List<FollowUpActivityBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(FollowUpActivityBean.class));
		
		if(null!=list && list.size()>0){
			return  list.get(0);
		}else{
			return new FollowUpActivityBean();
		}
		
	}
 
	 
}
