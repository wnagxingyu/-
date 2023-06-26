package com.example.wxy.service;
import com.example.wxy.entity.User;
import java.util.List;
public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
