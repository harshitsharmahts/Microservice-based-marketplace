package com.commerce.controller;

import com.commerce.model.Users;
import com.commerce.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller to handle all user based database operations.
 * @Author Harshit Sharma
 */

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(@Qualifier("usersServiceImpl") UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * <p>
     *     Adds the new user(User's basic information) into the database.
     * </p>
     *
     * @param user the users details that you want to store into the database.
     *             You do not need to provide the user id eill get generated by the service itself.
     *             see the {@link UsersService#addNewUser(Users)}
     * @return the new added user.
     */

    @PostMapping("/")
    public ResponseEntity<Users> addNewUser(@RequestBody Users user) {
        Users u = usersService.addNewUser(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    /**
     * <p>
     *     Updates the user's information into the database by the provided user.
     * </p>
     *
     * @param user object of {@link Users} class, with the properties that you want to update.
     *             for updation passed object must contain the user's id.
     * @return the updated user.
     */

    @PutMapping("/")
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        Users u = usersService.updateUser(user);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    /**
     * <p>
     *     Deletes the user's entire information for the provided user's id.
     * </p>
     *
     * @param id the users's id that you want to remove from the database.
     * @return void.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        usersService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     *
     * @param email
     * @return
     */
    @GetMapping("/get/{email}")
    public ResponseEntity<Users> getUser(@PathVariable("email") String email) {
        Users u = usersService.getUser(email);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping("/check")
    public String sayHello() {
        return "Users DB-Service \t\t--\t\t [UP]";
    }

}

