package com.nour.saray.infra.server;

import com.nour.saray.domain.model.User;
import com.nour.saray.domain.ports.server.UserProvider;
import com.nour.saray.infra.server.mapper.UserEntityMapper;
import com.nour.saray.infra.server.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;


@Repository
public class UserProviderImp implements UserProvider {

    private final UserRepository userRepository;

    public UserProviderImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserById(final String id) {
        return UserEntityMapper.toDomain(userRepository.findById(id).orElse(null));
    }


    @Override
    public User create(User user) {
        com.nour.saray.infra.server.model.User userToSave = UserEntityMapper.toServer(user);
        userToSave.setId(SecurityContextHolder.getContext().getAuthentication().getName());
        return UserEntityMapper.toDomain(userRepository.save(userToSave));
    }


    @Override
    public User update(String id, User user) {
        return UserEntityMapper.toDomain(userRepository.save(UserEntityMapper.toServer(user)));
    }

    @Override
    public String delete(String id) {
        userRepository.deleteById(id);
        return "deleted";
    }

}
