package com.nour.saray.infra.server.mapper;

import com.nour.saray.infra.server.model.User;

public final class UserEntityMapper {


    private UserEntityMapper() {
    }

    public static com.nour.saray.domain.model.User toDomain(User user) {
        if (user == null) {
            return null;
        }
        return new com.nour.saray.domain.model.User(user.getId(),user.getEmail(), user.getCreationDate(),
                user.getTasks() == null ? null :
                user.getTasks().stream().map(TaskEntityMapper::toDomain).toList());
    }

    public static User toServer(com.nour.saray.domain.model.User user) {
        if (user == null) {
            return null;
        }
        return new User(user.id(),user.email(), user.creationDate(),
                user.tasks() == null ? null :
                user.tasks().stream().map(TaskEntityMapper::toServer).toList());
    }

}
