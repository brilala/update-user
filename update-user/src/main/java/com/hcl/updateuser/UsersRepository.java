package com.hcl.updateuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query("SELECT u FROM Users u WHERE u.username LIKE %?1%")
	public List<Users> findAll(String keyword);
}
