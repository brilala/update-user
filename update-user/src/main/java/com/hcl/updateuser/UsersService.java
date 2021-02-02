package com.hcl.updateuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersService {

	@Autowired
	private UsersRepository repo;

	public List<Users> listAll(String keyword) {
		if (keyword != null) {
			return repo.findAll(keyword);
		}
		return repo.findAll();
	}

	public void save(Users users) {
		repo.save(users);
	}

	public Users get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
}
