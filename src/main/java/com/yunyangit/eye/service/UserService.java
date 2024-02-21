package com.yunyangit.eye.service;

import java.util.List;

import com.yunyangit.eye.dto.UserDto;
import com.yunyangit.eye.model.SysUser;


public interface UserService {

	SysUser saveUser(UserDto userDto);

	SysUser updateUser(UserDto userDto);

	SysUser getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

	void changeUserstatus(Long id, Long status, String comment);
	
	void deleteUser(Long id);
	
	void saveUserPermission(Long userId, List<Long> permissionIds);

}
