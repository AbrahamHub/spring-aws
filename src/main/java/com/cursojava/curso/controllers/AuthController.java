package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UserDao;
import com.cursojava.curso.models.User;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController{
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value = "api/login" ,method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        User userLog = userDao.authCredential(user);
        if (userLog != null) {
            String token = jwtUtil.create(String.valueOf(userLog.getId()), userLog.getEmail());
            return token;
        }
        return "FAIL";
    }
}
