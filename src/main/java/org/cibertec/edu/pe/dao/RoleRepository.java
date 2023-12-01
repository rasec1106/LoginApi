package org.cibertec.edu.pe.dao;

import java.util.Optional;

import org.cibertec.edu.pe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	public Optional<Role> findByRoleName(String roleName);
}
