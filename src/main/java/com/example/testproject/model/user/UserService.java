package com.example.testproject.model.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;

    public void save(User user) {
        repository.save(user);
    }
    public List<User> getAllUsers() {
        List<User> users =new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(users::add);
        return users;
    }
    public void delete(User user) {
        repository.delete(user);
    }
    public List<User> getUsersByIdList(List<Long> user_id) {
        return repository.findUsersByIdList(user_id);
    }
    public User getUsersByEmail(String email) {
        return repository.findByEmail(email);
    }
    public User getUsersById(long id) {
        return repository.findUserById(id);
    }

}
