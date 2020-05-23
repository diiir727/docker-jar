package ru.otus.crud.controllers;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.crud.controllers.resp.UserResponse;
import ru.otus.crud.logic.UsersService;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Timed(value = "get_user", percentiles = {0.5, 0.95, 0.99})
    @GetMapping(produces = "application/json")
    public UserResponse get(@RequestParam(name = "id") String id) {
        var user = this.usersService.get(id);
        UserResponse resp = new UserResponse();
        resp.setId(id);
        resp.setName(user.getName());
        return resp;
    }

    @Timed(value = "create_user", percentiles = {0.5, 0.95, 0.99})
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequest user) {
        var cratedUser = this.usersService.add(user.getName());
        return ResponseEntity.created(URI.create("/users?id=" + cratedUser.getId()))
                .build();
    }

    @Timed(value = "update_user", percentiles = {0.5, 0.95, 0.99})
    @PutMapping(produces = "application/json")
    public UserResponse update(@RequestBody UserRequest userRequest) {
        var user = this.usersService.update(userRequest);
        UserResponse resp = new UserResponse();
        resp.setId(user.getId());
        resp.setName(user.getName());
        return resp;
    }

    @Timed(value = "delete_user", percentiles = {0.5, 0.95, 0.99})
    @DeleteMapping(produces = "application/json")
    public ResponseEntity<?> delete(@RequestParam(name = "id") String id) {
        this.usersService.delete(id);
        return ResponseEntity.ok().build();
    }
}
