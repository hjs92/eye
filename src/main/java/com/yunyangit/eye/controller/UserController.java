package com.yunyangit.eye.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyangit.eye.annotation.LogAnnotation;
import com.yunyangit.eye.dao.RoleDao;
import com.yunyangit.eye.dao.UserDao;
import com.yunyangit.eye.dto.UserDto;
import com.yunyangit.eye.model.SysUser;
import com.yunyangit.eye.page.table.PageTableHandler;
import com.yunyangit.eye.page.table.PageTableHandler.CountHandler;
import com.yunyangit.eye.page.table.PageTableHandler.ListHandler;
import com.yunyangit.eye.page.table.PageTableRequest;
import com.yunyangit.eye.page.table.PageTableResponse;
import com.yunyangit.eye.service.UserService;
import com.yunyangit.eye.utils.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户相关接口
 *
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@LogAnnotation
	@PostMapping("/saveUser")
	@ApiOperation(value = "保存用户")
	public SysUser saveUser(@RequestBody UserDto userDto) {
		SysUser u = userService.getUser(userDto.getUsername());
		if (u != null) {
			throw new IllegalArgumentException(userDto.getUsername() + "已存在");
		}

		return userService.saveUser(userDto);
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改用户")
	public SysUser updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}

	@LogAnnotation
	@PutMapping("/changePassword/{username}")
	@ApiOperation(value = "修改密码")
	public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
		
		userService.changePassword(username, oldPassword, newPassword);
	}

	@PreAuthorize("hasAuthority('sys:user:query')")
	@GetMapping
	@ApiOperation(value = "用户列表")
	public PageTableResponse listUsers(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return userDao.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<SysUser> list(PageTableRequest request) {
				List<SysUser> list = userDao.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@ApiOperation(value = "当前登录用户")
	@GetMapping("/current")
	public SysUser currentUser() {
		return UserUtil.getLoginUser();
	}

	@ApiOperation(value = "根据用户id获取用户")
	@GetMapping("/{id}")
	public SysUser user(@PathVariable Long id) {
		return userDao.getById(id);
	}
	
	@LogAnnotation
	@PutMapping("/userId={id}")
	@ApiOperation(value = "修改用户状态")
	public void changeUserstatus(@PathVariable Long id, Long status, String comment) {
		userService.changeUserstatus(id, status, comment);
		
	}
	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除用户")
	public void delete(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	@LogAnnotation
	@PostMapping("/saveUserPermission/{userId}/{permissionIds}")
	@ApiOperation(value = "添加/修改用户权限")
	public void saveUserPermission(@PathVariable Long userId,@PathVariable List<Long> permissionIds) {
		userService.saveUserPermission(userId, permissionIds);
	}
	

}
