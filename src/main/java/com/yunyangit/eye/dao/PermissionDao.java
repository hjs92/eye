package com.yunyangit.eye.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yunyangit.eye.model.Permission;


@Mapper
public interface PermissionDao {

	@Select("select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = #{userId} order by p.sort")
	List<Permission> listByUserId(@Param("userId") Long userId);

	@Select("select p.*, b.menu as name from sys_permission p inner join menuLanguage b on p.id = b.menuId inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} and languageId = #{languageID} order by p.sort")
	List<Permission> listByRoleId(@Param("roleId") Long roleId, @Param("languageID") String languageID);
	
	@Select("select permissionId from sys_role_permission where userId = #{userId}")
	List<Long> getPermissionByUserId(@Param("userId") Long userId);
	
	@Select("select a.*, b.menu as name from sys_permission a inner join menuLanguage b on a.id = b.menuId where a.id = #{id} and languageId = #{languageID}")
	Permission getById(@Param("id")  Long id, @Param("languageID") String languageID);

	@Insert("insert into sys_permission(parentId, name, icon, href, type, permission, sort) values(#{parentId}, #{name}, #{icon}, #{href}, #{type}, #{permission}, #{sort})")
	int save(Permission permission);

	@Update("update sys_permission  set parentId = #{parentId}, name = #{name}, icon = #{icon}, href = #{href}, type = #{type}, permission = #{permission}, sort = #{sort} where id = #{id}")
	int update(Permission permission);

	@Delete("delete from sys_permission where id = #{id}")
	int delete(Long id);

	@Delete("delete from sys_permission where parentId = #{id}")
	int deleteByParentId(Long id);

	@Delete("delete from sys_role_permission where permissionId = #{permissionId}")
	int deleteRolePermission(Long permissionId);

	@Select("select ru.userId from sys_role_permission rp inner join sys_role_user ru on ru.roleId = rp.roleId where rp.permissionId = #{permissionId}")
	Set<Long> listUserIds(Long permissionId);
}
