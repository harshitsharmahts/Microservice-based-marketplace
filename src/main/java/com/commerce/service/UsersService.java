package com.commerce.service;

import com.commerce.model.Users;

/**
 * <p>
 * Interface to provide abstract operations on database.
 *
 * @Author Harshit Sharma
 */
public interface UsersService {

	/**
	 * <p>
	 * Abstract method, that is responsible for inserting the new user into the database.
	 * @param user the user actual body.
	 * @return
	 */

	Users addNewUser(Users user);

	/**
	 * <p>
	 *     Abstract method to update the already existing user's information.
	 * </p>
	 * @param user
	 * @return
	 */
	Users updateUser(Users user);

	/**
	 * <p>
	 * 		Abstract method to delete the user.
	 * </p>
	 * @param authToken
	 */
	void deleteUser(String authToken);

	/**
	 * <p>
	 *     Abstract method to get the user by email.
	 * </p>
	 * @param email
	 * @return
	 */
	Users getUser(String email);

}
