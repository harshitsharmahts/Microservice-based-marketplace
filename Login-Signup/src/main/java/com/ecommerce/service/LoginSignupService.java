package com.ecommerce.service;


import com.ecommerce.model.LoginCredentials;
import com.ecommerce.model.Users;
import com.ecommerce.model.response.JSONResponse;

/**
 * <p>
 *     This interface will provide the support to controller to handle the requests.
 *     All the implementation of this interface will have the business logic to check the legitimacy of user
 *     and to add the new user.
 * </p>
 * @author Harshit Sharma
 */
public interface LoginSignupService {
    /**
     * <p>
     *     This function's implementation will be responsible for checking the legitimacy of the User.
     * </p>
     * @param loginCredentials the {@link LoginCredentials} that contains the user's email and the password.
     *
     * @return the {@link JSONResponse} that binds the status of response along with the body.
     *          this body is mainly the information of user.
     */
    JSONResponse<Users> login(LoginCredentials loginCredentials);

    /**
     * <p>
     *     This abstract function's implementation will add the user for us.
     * </p>
     * @param user the user's basic details.
     *             email & password must be provided.
     * @return the {@link JSONResponse} that binds the status of response along with the body.
     *          this body is mainly the information of user.
     *
     */
    JSONResponse<Users> signup(Users user);
    /**
     * <p>
     *     This abstract function's implementation would be responsible
     *     for checking the legitimacy of admin's credentials.
     * </p>
     * @param loginCredentials the {@link LoginCredentials} that contains the user's email and the password.
     *
     * @return the {@link JSONResponse} that binds the status of response along with the body.
     *          this body is mainly the information of user.
     *
     */
    JSONResponse<Users> loginAsManager(LoginCredentials loginCredentials);


}
