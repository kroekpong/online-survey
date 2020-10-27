package com.khotdee168.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.khotdee168.common.model.DDLBean;
import com.khotdee168.survey.model.QuestionGroupBean;
import com.khotdee168.survey.model.QuestionSetBean;

@Repository
public class DDLDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	 
	public List getActiveCompany() {
		StringBuilder sql = new StringBuilder();
		sql.append("select company_id code ,  company_code value , company_name name from tb_company where  status = 1 order by company_code ");
		List<DDLBean> list = jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper(DDLBean.class));
		return list;
	}

	public List getRoles() {
		StringBuilder sql = new StringBuilder();
		sql.append("select role_code code ,  role_code value , role_name name from tb_role ");
        List<DDLBean> list = jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper(DDLBean.class));
		return list;
	}
  

	public List getLocationType() {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		
		sql.append("  SELECT * FROM tb_system_config WHERE TYPE = 'LOCATION_TYPE'");
		sql.append(" order by code  ");
		List<DDLBean> list = jdbcTemplate.query(sql.toString(),wh.toArray(),new BeanPropertyRowMapper(DDLBean.class));
		return list;
	}
	
	public List getActiveWarehouse() {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		
		sql.append("  SELECT * FROM tb_system_config WHERE TYPE = 'WAREHOUSE'");
		sql.append(" order by code  ");
		List<DDLBean> list = jdbcTemplate.query(sql.toString(),wh.toArray(),new BeanPropertyRowMapper(DDLBean.class));
		return list;
	} 
	public List getLogType() {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		
		sql.append("  SELECT * FROM tb_system_config WHERE TYPE = 'LOG_TYPE'");
		sql.append(" order by code  ");
		List<DDLBean> list = jdbcTemplate.query(sql.toString(),wh.toArray(),new BeanPropertyRowMapper(DDLBean.class));
		return list;
	} 

	public List getQuestionGroup( ) {
		String lang = LocaleContextHolder.getLocale().getLanguage();
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select *, name_"+lang+" name  from tb_question_group where 1=1   ");
		sql.append(" order by id  ");
		List<QuestionGroupBean> list = jdbcTemplate.query(sql.toString(),wh.toArray(),new BeanPropertyRowMapper(QuestionGroupBean.class));
		return list;
	}

	public List getRepairType() {
		String lang = LocaleContextHolder.getLocale().getLanguage();
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * , name_"+lang+" name from tb_repair_type where 1=1   ");
		sql.append(" order by id  ");
		List<DDLBean> list = jdbcTemplate.query(sql.toString(),wh.toArray(),new BeanPropertyRowMapper(DDLBean.class));
		return list;
	} 

	public List getQuestionSet(boolean all) {
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_question_set where 1=1");
		if(!all){
			sql.append("  and status = 1   ");
		}
		sql.append(" order by id  ");
		List<QuestionSetBean> list = jdbcTemplate.query(sql.toString(),wh.toArray(),new BeanPropertyRowMapper(QuestionSetBean.class));
		return list;
	} 
	
	public List getDDLType(String type) {
		String lang = LocaleContextHolder.getLocale().getLanguage();
		List wh = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * , name_"+lang+" name from tb_sys_ddl where type = ?   ");
		sql.append(" order by sort   ");
		wh.add(type);
		List<DDLBean> list = jdbcTemplate.query(sql.toString(),wh.toArray(),new BeanPropertyRowMapper(DDLBean.class));
		return list;
	} 
	
	public String getSysParamValue(String type) {
		return jdbcTemplate.queryForObject("select value from tb_sys_param where type = ?", String.class, type);
	}
	
	public String getSysParamDesc(String type) {
		String lang = LocaleContextHolder.getLocale().getLanguage();
		return jdbcTemplate.queryForObject("select desc_"+lang+" name from tb_sys_param where type = ? ", String.class, type);
	}

	
}
