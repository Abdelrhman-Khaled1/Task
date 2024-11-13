package com.example.Task.Management.System.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("User with user Id: %d not found!", id));
    }
    public UserNotFoundException(String userName) {
        super(String.format("User with user user_name: %d not found!", userName));
    }
}
