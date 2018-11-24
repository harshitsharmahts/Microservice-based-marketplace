package com.commerce.service;

import com.commerce.model.Users;

/**
 * <p>
 * Interface to provide abstract operations on database.
 * And all the implementation of this interface will have the business logic to operate on database
 * and implementations will also help to access the database through REST API.
 *
 * @author Harshit Sharma
 */
public interface UsersService {

	/**
	 * <p>
	 * Abstract method, It's implementation will be responsible for inserting the new user into the database.
	 * @param user the users details that you want to store into the database.
	 *             You do not need to provide the user id it'll get generated by the model itself.
	 *
	 * @return the new added user.
	 */
	Users addNewUser(Users user);

	/**
	 * <p>
	 *     Abstract method, It's implementation will be responsible for update the already existing user's information.
	 * </p>
	 * @param user object of {@link Users} class, with the properties that you want to update.
	 *             for updation passed object must contain the user's id.
	 * @return the updated user.
	 */

	Users updateUser(Users user);

	/**
	 * <p>
	 * 		Abstract method, It's implementation will be responsible for deleting the user.
	 * </p>
	 *
	 * @param id the users's id that you want to remove from the database.
	 */
	void deleteUser(String id);

	/**
	 * <p>
	 *     Abstract method, It's implementation will be responsible for getting the user by email.
	 * </p>
	 * @param email the email of the user/
	 * @return the document stored in Users collection.
	 */
	Users getUser(String email);

}
