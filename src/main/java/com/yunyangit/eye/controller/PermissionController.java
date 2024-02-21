package com.yunyangit.eye.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyangit.eye.dao.PermissionDao;
import com.yunyangit.eye.dao.UserDao;
import com.yunyangit.eye.dto.LoginUser;
import com.yunyangit.eye.model.Permission;
import com.yunyangit.eye.service.PermissionService;
import com.yunyangit.eye.utils.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 权限相关接口
 */
@Api(tags = "权限菜单")
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserDao userDao;

	@ApiOperation(value = "当前登录用户拥有的权限")
	@GetMapping("/current")
	public List<Permission> permissionsCurrent() {
		LoginUser loginUser = UserUtil.getLoginUser();
		List<Permission> list = loginUser.getPermissions();
		final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1))
				.collect(Collectors.toList());

//		setChild(permissions);
//
//		return permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
		// 支持多级菜单
        List<Permission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });

        return firstLevel;
	}

	/**
	 * 设置子元素
	 *
	 * @param p
	 * @param permissions
	 */
	private void setChild(Permission p, List<Permission> permissions) {
		List<Permission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
		p.setChild(child);
		if (!CollectionUtils.isEmpty(child)) {
			child.parallelStream().forEach(c -> {
				//递归设置子元素，多级菜单支持
				setChild(c, permissions);
			});
		}
	}

	@GetMapping(params = "roleId")
	@ApiOperation(value = "根据角色id获取权限")
	public List<Permission> listByRoleId(Long roleId) {
		LoginUser loginUser = UserUtil.getLoginUser();
		return permissionDao.listByRoleId(roleId,userDao.getLanguage(loginUser.getUsername()));
	}
	
	@GetMapping("/getPermissionByUserId")
	@ApiOperation(value = "根据用户id获取权限")
	public List<Long> listByUserId(Long userId) {
		return permissionDao.getPermissionByUserId(userId);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据菜单id获取菜单")
	public Permission get(@PathVariable Long id) {
		LoginUser loginUser = UserUtil.getLoginUser();
		return permissionDao.getById(id,userDao.getLanguage(loginUser.getUsername()));
	}

	@PostMapping
	@ApiOperation(value = "保存菜单")
	public void save(@RequestBody Permission permission) {
		permissionDao.save(permission);
	}
	
	@PutMapping
	@ApiOperation(value = "修改菜单")
	public void update(@RequestBody Permission permission) {
		permissionService.update(permission);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除菜单")
	public void delete(@PathVariable Long id) {
		permissionService.delete(id);
	}

	/**
	 * 校验权限
	 * 
	 * @return
	 */
	@GetMapping("/owns")
	@ApiOperation(value = "校验当前用户的权限")
	public Set<String> ownsPermission() {
		List<Permission> permissions = UserUtil.getLoginUser().getPermissions();
		if (CollectionUtils.isEmpty(permissions)) {
			return Collections.emptySet();
		}

		return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(Permission::getPermission).collect(Collectors.toSet());
	}
	
}
