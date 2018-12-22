package com.ecommerce.service;

import com.ecommerce.model.LoginCredentials;
import com.ecommerce.model.Users;
import com.ecommerce.model.constant.C;
import com.ecommerce.model.response.JSONResponse;
import com.ecommerce.service.rest.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     implementation of interface {@link LoginSignupService}.
 * </p>
 * @author Harshit Sharma
 */

@Service
public class LoginSignupServiceImpl implements LoginSignupService {

    private final Rest rest;

    @Autowired
    public LoginSignupServiceImpl(Rest rest) {
        this.rest = rest;
    }


    @Override
    public JSONResponse<Users> login(LoginCredentials loginCredentials) {

        Users u = rest.getUser(loginCredentials.getEmail());
        if(null == u)
            return new JSONResponse<Users>().setMessage(C.STATUS.USER_DOES_NOT_EXIST);
        else if (!u.getPassword().equals(loginCredentials.getPassword())) {
            return new JSONResponse<Users>().setMessage(C.STATUS.WRONG_PASSWORD);
        }

        return new JSONResponse<Users>()
                .setMessage(C.STATUS.LOGIN_SUCCESS)
                .setBody(u.setPassword(null));

    }

    @Override
    public JSONResponse<Users> signup(Users user) {
        Users u = rest.getUser(user.getEmail());
        if (null != u) return new JSONResponse<Users>().setMessage(C.STATUS.USER_ALREADY_EXIST);

        Users newUser = rest.addUser(user);
        return new JSONResponse<Users>()
                .setMessage(C.STATUS.SIGNUP_SUCCESS)
                .setBody(newUser.setPassword(null));
    }

    @Override
    public JSONResponse<Users> loginAsManager(LoginCredentials loginCredentials) {

        if(!loginCredentials.getEmail().equalsIgnoreCase("admin@microservice.com")) {
            return new JSONResponse<Users>().setMessage(C.STATUS.USER_DOES_NOT_EXIST);
        } else {
            if(!loginCredentials.getPassword().equals("India$123")) {
                return new JSONResponse<Users>().setMessage(C.STATUS.WRONG_PASSWORD);
            }

            Users adminUser = new Users()
                    .setFullName("Harshit Sharma")
                    .setEmail("admin@microservice.com")
                    .setPhoneNumber("+91 9587910916");

            return new JSONResponse<Users>()
                    .setMessage(C.STATUS.LOGIN_SUCCESS)
                    .setBody(adminUser);
        }

    }

}
