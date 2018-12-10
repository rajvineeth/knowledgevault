package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
@CrossOrigin
public class SecureController {

   private UserService userService;

    @Autowired
    public SecureController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/users")
    public String loginSuccess() {
        return "Login Successful!";
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public User findByUsername(@PathVariable String username) {
        System.out.println("secure");
//        System.out.println(userService.findByEmail(username));
        return userService.findByEmail(username);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user) {
        System.out.println(user);
        return userService.save(user);
    }
}
