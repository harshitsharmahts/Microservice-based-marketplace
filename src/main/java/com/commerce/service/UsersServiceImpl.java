package com.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.model.Users;
import com.commerce.repository.UsersRepository;


/**
 * <p>
 *      Implementation of interface {@link UsersService}
 * </p>
 * @Author Harshit Sharma
 * @see {@link UsersService}
 */

@Service
public class UsersServiceImpl implements UsersService {

	private final UsersRepository repo;

    @Autowired
    public UsersServiceImpl(UsersRepository repo) {
        this.repo = repo;
    }

    @Override
	public Users addNewUser(Users user) {
		return repo.insert(user);
	}

	@Override
	public Users updateUser(Users user) {
		return repo.save(user);
	}

    @Override
    public void deleteUser(String id) {
        repo.deleteById(id);
    }
}
