package com.commerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.model.Users;
import com.commerce.repository.UsersRepository;

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
	public List<String> addCheckedOutItem(String itemId, String authToken) {
		Optional<Users> opt = repo.findById(authToken);

		if (opt.isPresent()) {
            Users user = opt.get();
            List<String> list = user.getCheckoutList().orElse(new ArrayList<>());
            list.add(itemId);
            updateUser(user.setCheckoutList(list));
            return user.getCheckoutList().orElse(new ArrayList<>());
        }

        return new ArrayList<>();
	}

	@Override
	public List<String> deleteCheckedItem(String itemId, String authToken) {
		Optional<Users> opt = repo.findById(authToken);

        if (opt.isPresent()){
            Users user = opt.get();

            Optional<List<String>> optionalList = user.getCheckoutList();

            optionalList.ifPresent(list->{
                list.remove(itemId);
                updateUser(user.setCheckoutList(list));
            });

            return  user.getCheckoutList().orElse(new ArrayList<>());
        }

        return new ArrayList<>();
	}

	@Override
	public List<String> purchasedItem(String itemId, String authToken) {
        Optional<Users> opt = repo.findById(authToken);

        if(opt.isPresent()){

            Users user = opt.get();
            List<String> list = user.getCheckoutList().orElse(new ArrayList<>());
            list.add(itemId);
            updateUser(user.setCheckoutList(list));
            return user.getPurchaseHistory().orElse(new ArrayList<>());
        }

        return new ArrayList<>();
	}

    @Override
    public boolean suspend(String authToken) {
        Optional<Users> opt = repo.findById(authToken);

        if(opt.isPresent()){
            Users user = opt.get();
            repo.save(user.setSuspended(true));
            return true;
        }

        return false;
    }

    @Override
    public boolean unSuspend(String authToken) {
        Optional<Users> opt = repo.findById(authToken);

        if(opt.isPresent()){
            Users user = opt.get();
            repo.save(user.setSuspended(false));
            return true;
        }

        return false;
    }
}
