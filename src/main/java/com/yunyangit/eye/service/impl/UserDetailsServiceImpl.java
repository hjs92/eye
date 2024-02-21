package com.yunyangit.eye.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yunyangit.eye.dao.PermissionDao;
import com.yunyangit.eye.dto.LoginUser;
import com.yunyangit.eye.model.Permission;
import com.yunyangit.eye.model.SysUser;
import com.yunyangit.eye.model.SysUser.Status;
import com.yunyangit.eye.service.UserService;


/**
 * spring security登陆处理<br>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = userService.getUser(username);
		
		if (sysUser == null) {
//			throw new AuthenticationCredentialsNotFoundException("用户名不存在");
			throw new AuthenticationCredentialsNotFoundException("信息有误");
		} else if (sysUser.getStatus() == Status.LOCKED) {
			throw new LockedException("用户被锁定,请联系管理员");
		} else if (sysUser.getStatus() == Status.DISABLED) {
			//throw new DisabledException("用户已作废");
			throw new DisabledException("用户账号待审批");
		} else if (new Date().getTime() < sysUser.getAllowAt().getTime()){// 判断是否为允许登录时间
			throw new LockedException("账号锁定，还没到允许登录的时间");
		}
		

		LoginUser loginUser = new LoginUser();
		BeanUtils.copyProperties(sysUser, loginUser);
		List<Permission> permissions = permissionDao.listByUserId(sysUser.getId());
		loginUser.setPermissions(permissions);

		return loginUser;
	}

}
