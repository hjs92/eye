package com.yunyangit.eye.service;

import com.yunyangit.eye.model.Permission;



public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);

}
