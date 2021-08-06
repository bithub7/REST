package controller;

import model.User;
import repository.UserRepository;
import repository.hibernate.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class UserController {

    private UserRepository userRepository = new UserRepositoryImpl();
    private User model = new User();
    private User user;

    public void setUserId(Long id){
        this.model.setId(id);
    }

    public void setUserName(String name){
        this.model.setName(name);
    }

    public User saveUser(){
        try {
            user = userRepository.save(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User updateUser(){
        user = userRepository.update(model);
        return user;
    }

    public User getUserById(){
        user = userRepository.getById(model.getId());
        return user;
    }

    public List<User> getAllUsers(){
        List<User> userList = userRepository.getAll();
        return userList;
    }

    public void deleteByIdUser(){
        userRepository.deleteById(model.getId());
    }



}

