package com.commerce.controller;

import com.commerce.model.Users;
import com.commerce.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(@Qualifier("usersServiceImpl") UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String sayHello(){
        return "db-user-service\t\t[UP]";
    }

    @PostMapping("/add")
    public Users addNewUser(@RequestBody Users user) {
        return usersService.addNewUser(user);
    }

    @PostMapping("/update")
    public Users updateUser(@RequestBody Users user) {
        return usersService.updateUser(user);
    }

    @PostMapping(
            value = "/checkout",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public List<String> addCheckedItem(String itemId, String authToken) {
        return usersService.addCheckedOutItem(itemId,authToken);
    }


    @DeleteMapping(
            value = "/checkout",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public List<String> deleteCheckedItem(String itemId,String authToken) {
        return usersService.deleteCheckedItem(itemId,authToken);
    }

    @PostMapping(
            value = "/purchased",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public List<String> purchased(@RequestParam("item_id") String itemId, @RequestParam("auth_token") String authToken) {
        return usersService.purchasedItem(itemId,authToken);
    }

    @PostMapping("/suspend")
    public boolean suspend(String authToken) {
        return usersService.suspend(authToken);
    }


    @PostMapping("/un-suspend")
    public boolean unSuspend(String authToken) {
        return usersService.unSuspend(authToken);
    }
}