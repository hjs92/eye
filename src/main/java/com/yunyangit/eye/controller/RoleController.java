package com.yunyangit.eye.controller;


import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.yunyangit.eye.annotation.LogAnnotation;
import com.yunyangit.eye.dao.RoleDao;
import com.yunyangit.eye.dto.RoleDto;
import com.yunyangit.eye.model.Role;
import com.yunyangit.eye.model.SysUser;
import com.yunyangit.eye.page.table.PageTableHandler;
import com.yunyangit.eye.page.table.PageTableHandler.CountHandler;
import com.yunyangit.eye.page.table.PageTableHandler.ListHandler;
import com.yunyangit.eye.page.table.PageTableRequest;
import com.yunyangit.eye.page.table.PageTableResponse;
import com.yunyangit.eye.service.RoleService;

/**
 * 角色相关接口
 * @author Administrator
 *
 */
@Api(tags = "角色")
@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleDao roleDao;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存角色")
	public void saveRole(@RequestBody RoleDto roleDto){
		roleService.saveRole(roleDto);
		
	}
	
	@GetMapping("/roleList")
	@ApiOperation(value = "角色列表")
	public PageTableResponse listRoles(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return roleDao.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<Role> list(PageTableRequest request) {
				List<Role> list = roleDao.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	
	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取角色")
	public Role get(@PathVariable Long id) {
		return roleDao.getById(id);
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "所有角色")
	public List<Role> roles() {
		return roleDao.list(Maps.newHashMap(), null, null);
	}
	
	@GetMapping(params = "userId")
	@ApiOperation(value = "根据用户id获取拥有的角色")
	public List<Role> roles(Long userId) {
		return roleDao.listByUserId(userId);
	}
	
	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除角色")
	public void delete(@PathVariable Long id) {
		roleService.deleteRole(id);
	}
	
	@GetMapping("/getRoleUser/roleId={roleId}")
	@ApiOperation(value = "通过角色ID返回对应用户列表")
	public List<SysUser> getRoleUser(@PathVariable Long roleId) {
		return roleDao.getRoleUser(roleId);
	}
	
	@LogAnnotation
	@DeleteMapping("/{userId}/{roleId}")
	@ApiOperation(value = "角色中删除用户")
	public void deleteRoleIDUserID(@PathVariable Long userId,@PathVariable Long roleId) {
		roleService.deleteRoleIDUserID(userId,roleId);
	}
	
	@LogAnnotation
	@PostMapping("/{userId}/{roleId}")
	@ApiOperation(value = "新增角色用户关系")
	public void saveUserRole(@PathVariable Long userId,@PathVariable Long roleId) {
		roleService.saveUserRole(userId,roleId);
	}
	
	
	
}
