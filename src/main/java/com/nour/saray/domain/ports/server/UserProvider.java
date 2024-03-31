package com.nour.saray.domain.ports.server;


import com.nour.saray.domain.model.User;

public interface UserProvider {

    User getUserById(final String id);

    User create(User user);

    User update(String id, User user);

    String delete(String id);
}
