package org.docker.start.controller;

import lombok.RequiredArgsConstructor;
import org.docker.start.entity.UserEntity;
import org.docker.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserEntity> getUser(@RequestParam Long id) {
        UserEntity user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> users = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PutMapping
    public ResponseEntity<Void> createUser(@RequestBody UserEntity user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
