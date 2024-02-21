package com.yunyangit.eye.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yunyangit.eye.model.SysUser;


@Mapper
public interface UserDao {
	
	@Update("update sys_user set language = #{language} where username = #{username}")
	int updateLanguage(@Param("language") String language, @Param("username") String username);
	
	@Select("select language from sys_user where username = #{username}")
	String getLanguage(String username);
	
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_user(username,password,status,pwdexpireTime,type,isenabled,allowAt,errorNum,realName,organization,job,createTime) "
			+ "values (#{username}, #{password}, #{status}, getdate(), #{type}, 0, getdate(), 0,#{realName},#{organization},#{job}, getdate())")
	int save(SysUser user);

	@Select("select * from sys_user where id = #{id}")
	SysUser getById(Long id);

	@Select("select * from sys_user where username = #{username}")
	SysUser getUser(String username);

	@Update("update sys_user set password = #{password} where id = #{id}")
	int changePassword(@Param("id") Long id, @Param("password") String password);
	
	@Update("update sys_user set isenabled = 1 where id = #{id}")
	int changeEnable(@Param("id") Long id);

	Integer count(@Param("params") Map<String, Object> params);

	List<SysUser> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
			@Param("limit") Integer limit);

	@Delete("delete from sys_role_user where userId = #{userId}")
	int deleteUserRole(Long userId);

	int saveUserRoles(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);
	
	int saveUserPermission(@Param("userId") Long userId, @Param("permissionIds") List<Long> permissionIds);

	int update(SysUser user);
	
	@Update("update sys_user set status = #{status} where id = #{id}")
	int changeUserstatus(@Param("status") Long status, @Param("id") Long id);
	
	@Delete("delete from sys_user where id = #{id}")
	int delete(Long id);
	
	@Delete("delete from sys_role_user where userId = #{userId}")
	int deleteRoleUser(Long userId);
	
	@Delete("delete from sys_role_permission where userId = #{userId}")
	int deleteUserPermission(Long userId);
}
