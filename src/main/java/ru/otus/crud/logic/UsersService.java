package ru.otus.crud.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.crud.controllers.UserRequest;
import ru.otus.crud.dao.UserEntity;
import ru.otus.crud.dao.UsersRepository;

import java.util.UUID;

@Component
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity add(String name) {
        checkName(name);

        var user = new UserEntity();
        user.setName(name);
        user.setId(UUID.randomUUID().toString());
        return this.usersRepository.insert(user);
    }

    public UserEntity get(String id) {
        return this.usersRepository.findById(id)
                .orElseThrow();
    }

    public void delete(String id) {
        this.usersRepository.deleteById(id);
    }

    public UserEntity update(UserRequest userRequest) {
        checkName(userRequest.getName());
        var user = this.usersRepository.findById(userRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("not exist user"));
        user.setName(userRequest.getName());
        this.usersRepository.save(user);
        return user;
    }

    private void checkName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("invalid name");
    }
}
