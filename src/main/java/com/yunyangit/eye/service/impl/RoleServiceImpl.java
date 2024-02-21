package com.yunyangit.eye.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yunyangit.eye.dao.RoleDao;
import com.yunyangit.eye.dto.RoleDto;
import com.yunyangit.eye.model.Role;
import com.yunyangit.eye.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	private static final Logger log = LoggerFactory.getLogger("adminLogger");
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	@Transactional
	public void saveRole(RoleDto roleDto) {
		Role role = roleDto;
		List<Long> permissionIds = roleDto.getPermissionIds();
		permissionIds.remove(0L);

		if (role.getId() != null) {// 修改
			updateRole(role, permissionIds);
		} else {// 新增
			saveRole(role, permissionIds);
		}
	}
	private void saveRole(Role role, List<Long> permissionIds) {
		Role r = roleDao.getRole(role.getName());
		if (r != null) {
			throw new IllegalArgumentException(role.getName() + "已存在");
		}
		roleDao.save(role);
		if (!CollectionUtils.isEmpty(permissionIds)) {
			roleDao.saveRolePermission(role.getId(), permissionIds);
		}
		log.debug("新增角色{}", role.getName());
	}

	private void updateRole(Role role, List<Long> permissionIds) {
		Role r = roleDao.getRole(role.getName());
		if (r != null && r.getId() != role.getId()) {
			throw new IllegalArgumentException(role.getName() + "已存在");
		}

		roleDao.update(role);

		roleDao.deleteRolePermission(role.getId());
		if (!CollectionUtils.isEmpty(permissionIds)) {
			roleDao.saveRolePermission(role.getId(), permissionIds);
		}
		log.debug("修改角色{}", role.getName());
	}
	
	@Override
	@Transactional
	public void deleteRole(Long id) {
		roleDao.deleteRolePermission(id);
		roleDao.deleteRoleUser(id);
		roleDao.delete(id);

		log.debug("删除角色id:{}", id);
	}
	@Override
	@Transactional
	public void deleteRoleIDUserID(Long userId, Long roleId){
		roleDao.deleteRoleIDUserID(userId,roleId);
		log.debug("角色id:{}删除用户id:{}", userId,roleId);
	}
	
	@Override
	@Transactional
	public void  saveUserRole(Long userId, Long roleId){
		roleDao.deleteRoleIDUserID(userId,roleId);
		roleDao.saveUserRole(userId, roleId);
		log.debug("新增角色{}用户{}关系", userId, roleId);
	}
}
