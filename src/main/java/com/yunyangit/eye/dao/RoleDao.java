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

import com.yunyangit.eye.model.Role;
import com.yunyangit.eye.model.SysUser;


@Mapper
public interface RoleDao {
	
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_role(name, description, status) values(#{name}, #{description}, #{status})")
	int save(Role role);
	
	int count(@Param("params") Map<String, Object> params);
	
	List<Role> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
			@Param("limit") Integer limit);
	
	@Select("select * from sys_role where id = #{id}")
	Role getById(Long id);
	
	@Select("select * from sys_role  where name = #{name}")
	Role getRole(String name);
	
	@Update("update sys_role set name = #{name}, description = #{description}, status = #{status} where id = #{id}")
	int update(Role role);
	
	@Select("select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = #{userId}")
	List<Role> listByUserId(Long userId);
	
	@Delete("delete from sys_role_permission where roleId = #{roleId}")
	int deleteRolePermission(Long roleId);
	
	int saveRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);
	
	@Delete("delete from sys_role where id = #{id}")
	int delete(Long id);
	
	@Delete("delete from sys_role_user where roleId = #{roleId}")
	int deleteRoleUser(Long roleId);
	
	@Delete("delete from sys_role_user where userId = #{userId} and roleId = #{roleId}")
	int deleteRoleIDUserID(@Param("userId") Long userId, @Param("roleId") Long roleId);
	
	@Select("select * from sys_user a inner join sys_role_user b on a.id = b.userId  where b.roleId = #{roleId}")
	List<SysUser> getRoleUser(Long roleId);
	
	@Insert("INSERT INTO sys_role_user (userId, roleId) VALUES (#{userId}, #{roleId})")
	int saveUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
	
}
