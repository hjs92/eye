package com.yunyangit.eye.dto;

import java.util.List;

import com.yunyangit.eye.model.Role;


public class RoleDto extends Role {

	private static final long serialVersionUID = 4218495592167610193L;

	private List<Long> permissionIds;

	public List<Long> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Long> permissionIds) {
		this.permissionIds = permissionIds;
	}
}
