package com.khotdee168.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.khotdee168.common.model.DDLBean;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.common.model.UserBean;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean insert(UserBean user) {
		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO tb_user (  ");
		sql.append(" NAME,  				");
		sql.append(" company_id,  				");
		sql.append(" user_id,  				");
		sql.append(" PASSWORD,  			");
		sql.append(" tel,   				");
		sql.append(" create_date,  			");
		sql.append(" create_by,  			");
		sql.append(" email,  				");
		sql.append(" enabled )   			");
		sql.append(" VALUES ( ?,?,?,?,?,NOW(),?,?,? ) ");

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode(user.getPassword());



		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getCompanyId());
				ps.setString(3, user.getUserId().trim());
				ps.setString(4, hashedPassword);
				ps.setString(5, user.getTel());
				ps.setString(6, user.getCreateBy());
				ps.setString(7, user.getEmail());
				ps.setString(8, user.getStatus());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();

		StringBuilder sqlRole = new StringBuilder();
		sqlRole.append(" INSERT INTO tb_user_role  				 ");
		sqlRole.append(" (user_id, authority) VALUES ( ?, ?)  	 ");

		return jdbcTemplate.update(
				sqlRole.toString(),
				user.getUserId(), user.getRole())> 0;

	}
	
	public boolean update(UserBean user) {

		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE tb_user SET			");
		sql.append(" NAME = ? ,   				");
		sql.append(" company_id = ? ,   			");	
		
		if(StringUtils.isNotEmpty(user.getPassword())){
			sql.append(" PASSWORD = ? ,  			");
		}
			
		sql.append(" tel = ? ,  				");
		sql.append(" update_date = NOW() ,    	");
		sql.append(" update_by = ? ,   			");
		sql.append(" email = ? ,   				");
		sql.append(" enabled = ? 				");
		sql.append(" WHERE id =? 				");
		
		


		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				int num = 1 ;
				
				ps.setString(num++, user.getName());
				ps.setString(num++, user.getCompanyId());
				if(StringUtils.isNotEmpty(user.getPassword())){

				PasswordEncoder encoder = new BCryptPasswordEncoder();
				String hashedPassword = encoder.encode(user.getPassword());

					ps.setString(num++, hashedPassword);
				}			
				ps.setString(num++, user.getTel());
				ps.setString(num++, user.getUpdateBy());
				ps.setString(num++, user.getEmail());
				ps.setString(num++, user.getStatus());
				ps.setString(num++, user.getId());
				return ps;
			}
		}, holder);
		

		jdbcTemplate.update("DELETE FROM tb_user_role WHERE user_id = ? ", user.getUserId()) ;
		
		

		StringBuilder sqlRole = new StringBuilder();
		sqlRole.append(" INSERT INTO tb_user_role  				 ");
		sqlRole.append(" (user_id, authority) VALUES ( ?, ?)  	 ");

		return jdbcTemplate.update(
				sqlRole.toString(),
				user.getUserId(), user.getRole())> 0;
	}

	
	public int count() {

		return jdbcTemplate.queryForObject(" SELECT COUNT(*) FROM tb_user WHERE enabled = 1  ", Integer.class);
	}
	
	
	public boolean delete(String id) {

		jdbcTemplate.update("DELETE FROM tb_user_role WHERE user_id in (select user_id from tb_user where id = ? ) ", id) ;
		
		return jdbcTemplate.update("DELETE FROM tb_user WHERE id = ? ", id) > 0;

	}


	public DataTableAjax getDataTable(UserBean param) {
		DataTableAjax result = new DataTableAjax();
		List wh = new ArrayList();

		StringBuilder sql = new StringBuilder();
			
		sql.append(" SELECT tu.*,  s.company_name company_name , tur.authority role ,tr.role_name,  tu.enabled as status FROM tb_user tu   ");
		sql.append(" LEFT JOIN  tb_company s ON tu.company_id = s.company_id  ");
		sql.append(" LEFT JOIN  tb_user_role tur ON tu.user_id = tur.user_id  ");
		sql.append(" LEFT JOIN  tb_role tr ON tr.role_code =  tur.authority  WHERE 1=1  ");
		
		
		if (StringUtils.isNotEmpty(param.getName())) {
			wh.add("%" + param.getName() + "%");
			sql.append(" AND tu.name like ? ");
		}
		
		
		if (StringUtils.isNotEmpty(param.getEmail())) {
			wh.add("%" + param.getEmail() + "%");
			sql.append(" AND tu.email like ? ");
		}
		
		if (StringUtils.isNotEmpty(param.getTel())) {
			wh.add("%" + param.getTel() + "%");
			sql.append(" AND tu.tel like ? ");
		}
		
		if (StringUtils.isNotEmpty(param.getRole())) {
			wh.add(param.getRole());
			sql.append(" AND tur.authority = ? ");
		}
		
		if (StringUtils.isNotEmpty(param.getCompanyId())) {
			wh.add(param.getCompanyId());
			sql.append(" AND tu.company_id  = ? ");
		}
		
		if (StringUtils.isNotEmpty(param.getStatus())) {
			wh.add(param.getStatus());
			sql.append(" AND tu.enabled =  ? ");
		}


		sql.append(" ORDER BY id desc  ");

		List<UserBean> list = jdbcTemplate.query(sql.toString(), wh.toArray(),
				new BeanPropertyRowMapper(UserBean.class));
		int total = list != null ? list.size() : 0;
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(list);
		return result;
	}

	public boolean checkDuplicate(UserBean bean) {
		return jdbcTemplate.queryForObject("select count(1) from tb_user where  user_id = ?  ",
				Integer.class, bean.getUserId()) > 0;
	}
	
	public List getRolesFunction() {
		StringBuilder sql = new StringBuilder();
		sql.append("select fn_id code ,  role_code value , fn_name name from tb_role_function ");
        List<DDLBean> list = jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper(DDLBean.class));
		return list;
	}
 
	
	
	
}


