package com.example.Task.Management.System.Service;

import com.example.Task.Management.System.Entity.User.User;
import com.example.Task.Management.System.Exception.UserNotFoundException;
import com.example.Task.Management.System.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
        return user;
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
        return user;
    }

    public void deleteUserAccount(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
