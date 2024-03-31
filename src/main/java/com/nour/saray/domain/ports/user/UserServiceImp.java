package com.nour.saray.domain.ports.user;


import com.nour.saray.domain.model.User;
import com.nour.saray.domain.ports.server.UserProvider;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {

    private final UserProvider userProvider;

    public UserServiceImp(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @Override
    public User getUserById(String id) {
        return userProvider.getUserById(id);
    }


    @Override
    public User create(User user) {
        return userProvider.create(user);
    }

    @Override
    public User update(String id, User user) {
        return userProvider.update(id, user);
    }

    @Override
    public String delete(String id) {
        return userProvider.delete(id);
    }

}
