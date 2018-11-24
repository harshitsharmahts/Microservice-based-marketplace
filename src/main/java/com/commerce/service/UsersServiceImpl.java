package com.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.model.Users;
import com.commerce.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * <p>
 *      Implementation of interface {@link UsersService}
 * </p>
 * @author Harshit Sharma
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

    @Override
    public Users getUser(String email) {
        Optional<List<Users>> optional = repo.findByEmail(email);
        List<Users> list = optional.orElse(new ArrayList<>());

        if(list.size()>0)
            return list.get(0);
        return null;
    }
}
