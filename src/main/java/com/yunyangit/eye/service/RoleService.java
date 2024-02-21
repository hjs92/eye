package com.yunyangit.eye.service;



import org.apache.ibatis.annotations.Param;

import com.yunyangit.eye.dto.RoleDto;


public interface RoleService {

	void saveRole(RoleDto roleDto);
	
	void deleteRole(Long id);
	
	void deleteRoleIDUserID(Long userId, Long roleId);
	
	void saveUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
