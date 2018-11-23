package com.ecommerce.controller;

import com.ecommerce.model.LoginCredentials;
import com.ecommerce.model.Users;
import com.ecommerce.model.response.LoginSignupResponseBody;
import com.ecommerce.service.LoginSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     Controller for the authentication purpose, this will be responsible to authenticate the login credentials and
 *     to insert a new User.
 * </p>
 * @author Harshit Sharma
 */

@RestController
public class LoginSignupController {

    private final LoginSignupService service;

    @Autowired
    public LoginSignupController(LoginSignupService service) {
        this.service = service;
    }

    /**
     * <p>
     *     Authenticates user's credentials (checks the attempted login is legit or not).<br><br>
     *     Endpoint: /login <br>
     *     HttpMethod: POST <br>
     *     Consumes: application/json <br>
     *     Produces: application/json <br>
     *     RequestBody: {
     *                      "email" : "user@mail.com",
     *                      "password" : "user's password"
     *                  }
     * </p>
     * @param loginCredentials the {@link LoginCredentials} that contains the user's email and the password.
     *
     * @return the {@link LoginSignupResponseBody} that binds the status of response along with the body.
     *          this body is mainly the information of user.
     *
     * @see {@link LoginSignupService#login(LoginCredentials)}
     */

    @PostMapping("/login")
    public ResponseEntity<LoginSignupResponseBody> login (@RequestBody LoginCredentials loginCredentials) {
        LoginSignupResponseBody s = service.login(loginCredentials);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    /**
     * <p>
     *     Registers a new user into the database.<br><br>
     *     Endpoint: /signup <br>
     *     HttpMethod: POST <br>
     *     Consumes: application/json <br>
     *     Produces: application/json <br>
     *     RequestBody: {
     *                        "fullName: "John Deo",
     *                        "email" : "user@mail.com",
     *                        "password" : "user's password",
     *                        "phoneNumber" : "+91 XXX XXX XXXX"
     *                   }
     *
     * </p>
     * @param user the user's basic details.
     *             email & password must be provided.
     * @return the {@link LoginSignupResponseBody} that binds the status of response along with the body.
     *          this body is mainly the information of user.
     *
     * @see {@link LoginSignupService#signup(Users)}
     */
    @PostMapping("/signup")
    public ResponseEntity<LoginSignupResponseBody> signup (@RequestBody Users user) {
        LoginSignupResponseBody s = service.signup(user);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    /**
     * <p>
     *     Authenticates admin user's credentials (checks the attempted login is legit or not).<br><br>
     *     Endpoint: /login-as-manager <br>
     *     HttpMethod: POST <br>
     *     Consumes: application/json <br>
     *     Produces: application/json <br>
     *     RequestBody: {
     *                       "email" : "user@mail.com",
     *                       "password" : "user's password"
     *                  }
     * </p>
     * @param loginCredentials the {@link LoginCredentials} that contains the user's email and the password.
     *
     * @return the {@link LoginSignupResponseBody} that binds the status of response along with the body.
     *          this body is mainly the information of user.
     *
     * @see {@link LoginSignupService#login(LoginCredentials)}
     */
    @PostMapping("/login-as-manager")
    public ResponseEntity<LoginSignupResponseBody> loginAsManager(LoginCredentials loginCredentials) {
        LoginSignupResponseBody b = service.loginAsManager(loginCredentials);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
}
