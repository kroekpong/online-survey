package com.khotdee168.survey.dao;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.khotdee168.common.dao.AbstractCommonJdbcDao;
import com.khotdee168.survey.model.RegisterBean;

@Repository
public class RegisterDao extends AbstractCommonJdbcDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public int insertRegister(RegisterBean bean) {
			final String sql = "INSERT INTO tb_register ("+
			" uid              "+
			",dealer_code     "+
			",dealer_name     "+
			",dealer_name_64  "+
			",customer        "+
			",customer_64     "+
			
			",tel             "+
			",license_64      "+
			",license         "+
			",repair_type_64  "+
			",repair_type     "+
			
			",ro_no           "+
			",stm_no          "+
			",sa_name_64      "+
			",sa_name         "+
			
//			",vote_flg        "+
//			",lock_flg        "+
			",content_type    "+
//			",channel         "+
//			",release_status  "
			" , create_date) "+
			" VALUES ( ?,?,?,?,?,? ,?,?,?,?,?, ?,?,?,?,? , NOW())";
			return jdbcTemplate.update(sql,
					 bean.getUid(),
					 bean.getAscCode(),
					 decode(bean.getAscName()),
					 bean.getAscName(),
					 decode(bean.getName()),
					 bean.getName(),
					 
					 bean.getTel(),
					 bean.getLicenseNo(),
					 decode(bean.getLicenseNo()),
					 bean.getRepairType(),
					 decode(bean.getRepairType()),
					 
					 bean.getRoNo(),
					 bean.getStmNo(),
					 bean.getSaName(),
					 decode(bean.getSaName()),
					 bean.getContentType() );
	}
	
	public boolean updateCancelFlg(RegisterBean bean) {
		return jdbcTemplate.update("update  tb_register  set lock_flg = 1 where uid = ?" , bean.getUid()) > 0;
	}
	 
	
	private String decode(String encodedString){
		if(null!=encodedString){
			byte[] actualByte= Base64.getDecoder().decode(encodedString);
			String enc = new String(actualByte);
			System.out.println(enc);
			return enc;
		}else{
			return encodedString;
		}
	}

	public boolean validUid(RegisterBean bean) {
		 return jdbcTemplate.queryForObject("select count(1) from tb_register where  uid = ?   ",Integer.class ,bean.getUid())>0;
	}

	 
	 
}
