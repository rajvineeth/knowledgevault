package com.stackroute.controller;

import com.stackroute.domain.Token;
import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user/")
@CrossOrigin("*")
public class UserController {

    private UserRepository userRepository;

   // @Value("${producer-service-impl.messages.exception1}")

    @Value("${usercontroller.messages.exception1}")
    private String exceptionMessage1;

    @Value("${usercontroller.messages.exception2}")
    private String exceptionMessage2;

    @Value("${usercontroller.messages.exception3}")
    private String exceptionMessage3;

    @Value("${usercontroller.messages.exception4}")
    private String exceptionMessage4;

    @Value("${usercontroller.messages.exception5}")
    private String exceptionMessage5;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("getall")
    public ResponseEntity<?> getallUsers(){
        ResponseEntity responseEntity;
        try{
              List<User> users=userRepository.findAll();
            responseEntity= new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
        }catch (Exception e){
            responseEntity= new ResponseEntity<String>( exceptionMessage1,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping("saveuser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        ResponseEntity responseEntity;
        try{
            userRepository.save(user);
            responseEntity= new ResponseEntity<String>(exceptionMessage2, HttpStatus.CREATED);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody User login) throws ServletException {

        String jwtToken = "";

        if (login.getUsername() == null || login.getPassword() == null) {
            throw new ServletException( exceptionMessage3);
        }

        String email = login.getUsername();
        String password = login.getPassword();

        User user = userRepository.findByusername(email);

        if (user == null) {
            throw new ServletException( exceptionMessage4);
        }

        String pwd = user.getPassword();

        if (!password.equals(pwd)) {
            throw new ServletException( exceptionMessage5);
        }
        System.out.println("got request");
        jwtToken = Jwts.builder().setSubject(email).claim("roles", email).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return new Token(user.getUsername(),jwtToken,user.getRole());
    }
}
