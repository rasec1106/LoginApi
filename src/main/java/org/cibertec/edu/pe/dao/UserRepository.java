package org.cibertec.edu.pe.dao;

import java.util.Optional;

import org.cibertec.edu.pe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	public Optional<User> findByUserName(String userName);
}
