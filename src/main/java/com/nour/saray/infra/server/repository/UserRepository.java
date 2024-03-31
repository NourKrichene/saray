package com.nour.saray.infra.server.repository;

import com.nour.saray.infra.server.model.User;

import java.util.Optional;

public interface UserRepository extends org.springframework.data.repository.CrudRepository<User, String> {


    @Override
    User save(User user);

    @Override
    Optional<User> findById(String id);

}
