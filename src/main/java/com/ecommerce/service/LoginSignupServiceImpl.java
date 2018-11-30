package com.ecommerce.service;

import com.ecommerce.model.LoginCredentials;
import com.ecommerce.model.Users;
import com.ecommerce.model.constant.C;
import com.ecommerce.model.response.LoginSignupResponseBody;
import com.ecommerce.service.amazon.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *     implementation of interface {@link LoginSignupService}.
 * </p>
 * @author Harshit Sharma
 */

@Service
public class LoginSignupServiceImpl implements LoginSignupService {

    private final RestTemplate restTemplate;
    private final AmazonS3Service aWservice;

    @Autowired
    public LoginSignupServiceImpl(RestTemplate restTemplate, AmazonS3Service aWservice) {
        this.restTemplate = restTemplate;
        this.aWservice = aWservice;
    }

    @Override
    public LoginSignupResponseBody login(LoginCredentials loginCredentials) {
        HttpHeaders httpHeaders = getHeader();

        Users u = restTemplate
                .exchange(C.REST.GET_URL,HttpMethod.GET,new HttpEntity<>(httpHeaders),Users.class,loginCredentials.getEmail())
                .getBody();

        if(null == u)
            return new LoginSignupResponseBody().setStatus(C.STATUS.USER_DOES_NOT_EXIST);
        else if (!u.getPassword().equals(loginCredentials.getPassword())) {
            return new LoginSignupResponseBody().setStatus(C.STATUS.PASSWORD_DOES_NOT_MATCH);
        }

        return new LoginSignupResponseBody()
                .setStatus(C.STATUS.LOGIN_SUCCESS)
                .setBody(u);
    }

    private LoginSignupResponseBody signup(Users user) {
        HttpHeaders httpHeaders = getHeader();

        Users u = restTemplate
                .exchange(C.REST.GET_URL,HttpMethod.GET,new HttpEntity<>(httpHeaders),Users.class,user.getEmail())
                .getBody();

        if (null != u) return new LoginSignupResponseBody().setStatus(C.STATUS.USER_ALREADY_EXIST);

        HttpEntity<Users> httpEntity = new HttpEntity<>(user,httpHeaders);
        Users newUser = restTemplate
                .exchange(C.REST.ADD_URL, HttpMethod.POST,httpEntity,Users.class)
                .getBody();
        return new LoginSignupResponseBody()
                .setStatus(C.STATUS.SIGNUP_SUCCESS)
                .setBody(newUser);
    }

    @Override
    public LoginSignupResponseBody signup(String fullName, String email, String phoneNumber, String password, String address, MultipartFile profilePic) {
        String imgurl = aWservice.uploadFileTos3bucket(profilePic);
        Users u = new Users()
                .setFullName(fullName)
                .setAddress(address)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .setProfilePic(imgurl);

        return signup(u);
    }

    @Override
    public LoginSignupResponseBody loginAsManager(LoginCredentials loginCredentials) {
        if (!loginCredentials.getEmail().equals("admin@mypersonal.com") || !loginCredentials.getPassword().equals("India$123")) {
            return new LoginSignupResponseBody()
                    .setStatus(C.STATUS.LOGIN_FAILURE);
        }

        Users adminUser = new Users()
                .setFullName("Harshit Sharma")
                .setEmail("admin@mypersonal.com")
                .setPhoneNumber("+91 9587910916");
        return new LoginSignupResponseBody()
                .setStatus(C.STATUS.LOGIN_SUCCESS)
                .setBody(adminUser);
    }

    private HttpHeaders getHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
