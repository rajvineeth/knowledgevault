package com.stackroute.service;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.domain.UserDetails;
import com.stackroute.exception.NoSuchUserException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
@Service
public class UserImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${user-Service.message.exception1}")
    private String serviceException1;

    @Value("${user-Service.message.exception2}")
    private String serviceException2;

    @Override
    public UserDetails saveUser(UserDetails userDetails) throws UserAlreadyExistsException {
        if(userRepository.existsById(userDetails.getUsername())){
            throw new UserAlreadyExistsException(serviceException2);
        }
        else {
            UserDetails saveduser = userRepository.save(userDetails);
            System.out.println("from serviceimpl after saving"+saveduser);
            return saveduser;
        }
    }

    @Override
    public UserDetails updateUserById(String id, UserDetails userDetails)throws NoSuchUserException {
        UserDetails newuser=null;
        if(userRepository.existsById(id)) {
            newuser = getUserById(id);
            newuser.setFirstname(userDetails.getFirstname());
            newuser.setLastname(userDetails.getLastname());
            newuser.setUsername(userDetails.getUsername());
            newuser.setRole(userDetails.getRole());
            UserDetails savedmovie=userRepository.save(userDetails);
            return savedmovie;
        }
        else{
            throw new NoSuchUserException(serviceException1);
        }
    }

    @Override
    public UserDetails getUserById(String id) {
        UserDetails userDetails = userRepository.findById(id).get();
        System.out.println("from service"+userDetails);
        return userDetails;
    }

    @Override
    public Boolean deleteUserById(String id) throws NoSuchUserException {
        if(userRepository.existsById(id)==false){
            throw new NoSuchUserException(serviceException1);
        }
        else{
            UserDetails deletedUserDetails =getUserById(id);
            userRepository.delete(deletedUserDetails);
            return true;
        }
    }
}
