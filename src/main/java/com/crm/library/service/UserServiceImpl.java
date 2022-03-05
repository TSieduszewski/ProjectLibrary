package com.crm.library.service;

import com.crm.library.dao.UserRepository;
import com.crm.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByActive() {
        return userRepository.findAllByActive(true);
    }

    @Override
    public User findById(int id) {

        Optional<User> user = userRepository.findById(id);

        User tempUser;

        if(user.isPresent()){
            tempUser = user.get();
        } else {
            throw new RuntimeException("Nie znalazłem użytkownika");
        }
        return tempUser;

    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
