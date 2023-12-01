package org.cibertec.edu.pe.service;

import java.util.List;

import org.cibertec.edu.pe.entity.Role;

public interface RoleService {
	public List<Role> getRoles();
	public Role getRoleByRoleName(String roleName);
	public List<Role> getRolesByUserName(String userName);
	public Role createRole(Role role);
	public Role updateRole(Role role);
	public boolean deleteRole(String roleName);
}
