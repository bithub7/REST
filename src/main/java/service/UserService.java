package service;

import controller.UserController;
import model.User;

import java.util.List;

public class UserService {

    private UserController userRepository = new UserController();

    public User save(String name){
        userRepository.setUserName(name);
        return userRepository.saveUser();
    }

    public User update(Long id, String name) {
        userRepository.setUserId(id);
        userRepository.setUserName(name);
        return userRepository.updateUser();
    }

    public User getById(Long id) {
        userRepository.setUserId(id);
        return userRepository.getUserById();
    }

    public List<User> getAll() {
        return userRepository.getAllUsers();
    }

    public void deleteById(Long id) {
        userRepository.setUserId(id);
        userRepository.deleteByIdUser();
    }
}
