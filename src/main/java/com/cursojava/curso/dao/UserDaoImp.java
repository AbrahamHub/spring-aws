package com.cursojava.curso.dao;

import com.cursojava.curso.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        String query = "FROM  User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
         entityManager.remove(user);
    }

    @Override
    public void register(User user) {
         entityManager.merge(user);
    }

    @Override
    public User authCredential(User user) {
        String query = "FROM  User WHERE email = :email";
        List<User> list = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (list.isEmpty()){
            return null;
        }
        String passwordHashed = list.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create();

        if (argon2.verify(passwordHashed, user.getPassword())){
            return list.get(0);
        }
        return null;
    }

}
