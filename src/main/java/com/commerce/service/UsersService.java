package com.commerce.service;

import com.commerce.model.Users;

import java.util.List;

public interface UsersService {
	Users addNewUser(Users user);
	Users updateUser(Users user);
	List<String> addCheckedOutItem(String itemId, String authToken);
	List<String> deleteCheckedItem(String itemId, String authToken);
	List<String> purchasedItem(String itemId, String authToken);
	boolean suspend(String authToken);
	boolean unSuspend(String authToken);
}
