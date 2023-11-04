package org.cibertec.edu.pe.dao;

import org.cibertec.edu.pe.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
