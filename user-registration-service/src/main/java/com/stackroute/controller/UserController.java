package com.stackroute.controller;

import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.domain.User;
import com.stackroute.domain.UserDetails;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin
public class UserController {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @Value("${user-controller.messages.delete}")
    private String ControllerMessage1;

    @Value("${user-controller.messages.update}")
    private String ControllerMessage2;

    @Value("${user-Controller.message.created}")
    private String ControllerMessage3;

    @Value("${user-Conntroller.messages.exception1}")
    private String ControllerMessage4;

    @Value("${user-Controller.message.kafkatopic}")
    private String ControllerMessag5;

    private static final String TOPIC = "user-data";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("saveuser")
    public ResponseEntity<?> saveUser(@RequestBody UserDetails userDetails){
        ResponseEntity responseEntity;
        System.out.println("got request");

        User User = new User(userDetails.getUsername(), bCryptPasswordEncoder.encode(userDetails.getPassword()));
        try{
            System.out.println(userDetails);
            UserDetails u = userService.saveUser(userDetails);
            kafkaTemplate.send(TOPIC, User);
            responseEntity= new ResponseEntity<UserDetails>(u, HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
            e.printStackTrace();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        ResponseEntity responseEntity;
        try{
            UserDetails userDetails = userService.getUserById(id);
            responseEntity= new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage()) ;
            responseEntity= new ResponseEntity<String>(ControllerMessage4,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody UserDetails userDetails, @PathVariable("id") String id){
        ResponseEntity responseEntity;
        try {
            userService.updateUserById(id, userDetails);
            responseEntity = new ResponseEntity<String>(ControllerMessage2,HttpStatus.CREATED);
        }catch (Exception e){
            responseEntity =new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
        }
        return responseEntity;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try {
            userService.deleteUserById(id);
            responseEntity = new ResponseEntity<String>(ControllerMessage1,HttpStatus.CREATED);
        }catch (Exception e){
            responseEntity =new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
        }
        return responseEntity;
    }

}
