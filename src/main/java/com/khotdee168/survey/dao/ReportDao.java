package com.khotdee168.survey.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.khotdee168.common.dao.AbstractCommonJdbcDao;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.survey.model.ActivityBean;
import com.khotdee168.survey.model.QuestionSetBean;
import com.khotdee168.survey.model.ReportTrackingBean;

@Repository
public class ReportDao extends AbstractCommonJdbcDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ActivityDao dao;

	public DataTableAjax  getDataTable(QuestionSetBean param) {
		DataTableAjax  result = new DataTableAjax();
		List wh = new ArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select *  from tb_question_set where status = 1 ");
		 
		sql.append(" ORDER BY create_date  ");
		

//		System.out.println(sql.toString());

		List list = jdbcTemplate.query(sql.toString(), wh.toArray(),new BeanPropertyRowMapper(QuestionSetBean.class));
		int total = list != null ? list.size() : 0;
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(list);
		return result;
	}


	public ReportTrackingBean getTrackingReport(ActivityBean bean) {
		List wh = new ArrayList();
//		StringBuilder sql = new StringBuilder();
//		wh.add(sid);
		ReportTrackingBean trackingBean = new ReportTrackingBean();
		
		ActivityBean activityBean =  dao.getActivity(bean.getId());
		trackingBean.setActivity(activityBean);
		
		Integer total = 0;
		Integer pre = 0;
		Integer sms = 0;
		Integer cc = 0;
		
		
		String sql = "";
		if("1".equals(activityBean.getPreFlg())){
			
			sql = "SELECT   COUNT(1) FROM tb_register r ,tb_activity a "
				+"	WHERE   r.create_date >=  a.ro_start_date   "
				+"	AND r.create_date <= a.ro_end_date          "
				+"	 AND r.lock_flg = 0  AND  a.id = ?          " ;
			total = jdbcTemplate.queryForObject(sql,Integer.class ,bean.getId());
			
			sql += " AND r.vote_flg = 1 ";
			pre = jdbcTemplate.queryForObject(sql,Integer.class ,bean.getId());
			
		}else{
			
			String sqlTotal = "  SELECT COUNT(1) FROM  tb_activity_release a WHERE a.act_id = ? ";
			total = jdbcTemplate.queryForObject(sqlTotal,Integer.class ,bean.getId());
		}
		
		sql = "	  SELECT COUNT(1) FROM  tb_activity_vote  a WHERE a.act_id = ? AND a.channel = 'SMS'";
		sms = jdbcTemplate.queryForObject(sql,Integer.class ,bean.getId());
		
		sql = "	  SELECT COUNT(1) FROM  tb_activity_vote  a WHERE a.act_id = ? AND a.channel = 'CC'";
		cc = jdbcTemplate.queryForObject(sql,Integer.class ,bean.getId());
		
 
		Integer inc = (total-pre-sms-cc);
		trackingBean.setTotal(total);
		trackingBean.setPre(pre);
		trackingBean.setSms(sms);
		trackingBean.setCc(cc);
		trackingBean.setIncomplete(inc);
		
		trackingBean.setTotalP(new BigDecimal(100).setScale(2,BigDecimal.ROUND_DOWN));
		trackingBean.setPreP(calPercent(total,pre));
		trackingBean.setSmsP(calPercent(total,sms));
		trackingBean.setCcP(calPercent(total,cc));
		trackingBean.setIncompleteP(calPercent(total,inc));
		
		return trackingBean;
	}
	
	private BigDecimal calPercent(int total, int num){
		BigDecimal rs = BigDecimal.ZERO;
		if(total>0){
			double rsb =  ((double)num/(double)total)*100;
			System.out.println(  "rsb = "+rsb);
			rs=  BigDecimal.valueOf(rsb).setScale(2,BigDecimal.ROUND_DOWN);
		}
//		System.out.println(num+"/"+total+"*100 = "+rs);
		return rs;
	}
//	public void summaryData(FollowUpActivityBean bean) {
//		Integer total = jdbcTemplate.queryForObject("select count(1) from tb_activity_release where  act_id = ?  and channel = 'CC'  ",Integer.class ,bean.getId());
//		Integer completeNo = jdbcTemplate.queryForObject("select count(1) from tb_activity_release where  act_id = ?  and channel = 'CC'  and  complete_flg = 2 ",Integer.class ,bean.getId());
//		bean.setTotalNo(String.valueOf(total));
//		bean.setCompleteNo(String.valueOf(completeNo));
//	}

	 
	 
}
