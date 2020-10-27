package com.khotdee168.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.khotdee168.common.model.UserProfile;


@Repository
public class UserProfileDao extends AbstractCommonJdbcDao {

	final String sqlUserName = "select u.* ,s.company_name  from tb_user u left join tb_company s on  u.company_id = s.company_id where u.enabled = 1 and u.user_id = ? " ;
	final String sqlAuthorities = "select user_id, authority from tb_user_role where user_id = ?";

	public UserProfile findByUserName(String userId) {
		
		UserProfile user = executeQueryForObject(sqlUserName,
			new Object[] {
					userId
			},
			new RowMapper<UserProfile>() {
				@Override
				public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserProfile user = new UserProfile(userId, rs.getString("password"), findGrantedRoleByUserId(userId));
					user.setEnabled(rs.getString("enabled"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setTel(rs.getString("tel"));
					user.setCompanyId(rs.getString("company_id"));
					user.setCompanyName(rs.getString("company_name"));
					return user;
				}
			}
		);
		return user;
	}
	
	public List<GrantedAuthority> findGrantedRoleByUserId(String userId) {
		
		logger.info("findGrantedRoleByUserId userId={}");
		
		List<GrantedAuthority> grantedRoleList = executeQuery(sqlAuthorities,
			new Object[] {
				userId
			},
			new RowMapper<GrantedAuthority>() {
				@Override
				public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new SimpleGrantedAuthority(rs.getString("authority"));
				}
			}
		);
		
		return grantedRoleList;
	}

}
