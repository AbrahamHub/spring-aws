package com.cursojava.curso.controllers;
import com.cursojava.curso.dao.UserDao;
import com.cursojava.curso.models.User;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value = "api/users")
    public List<User> getUsers(@RequestHeader(value = "Authorization")String token)
    {  String userID = jwtUtil.getKey(token);
        if (userID == null){
            return new ArrayList<>();
        }
       return userDao.getUsers();
    }
    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create();
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.register(user);
    }
    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        userDao.delete(id);
    }
}