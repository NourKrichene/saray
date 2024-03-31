package com.nour.saray.domain.ports.user;

import com.nour.saray.domain.model.Task;
import com.nour.saray.domain.model.User;

import java.util.List;

public interface UserService {
    User getUserById(final String id);

    User create(User user);

    User update(String id, User user);

    String delete(String id);

}
