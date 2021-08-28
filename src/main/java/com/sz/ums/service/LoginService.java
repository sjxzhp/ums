package com.sz.ums.service;

import com.sz.ums.domain.User;
import com.sz.ums.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private UserRepo userRepo;
    public LoginService(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    public boolean checkUser(String username,String password){
        User user = userRepo.findUserByUsernameAndPassword(username, password);
        if (user!=null){
            return true;
        }
        return false;
    }
}
