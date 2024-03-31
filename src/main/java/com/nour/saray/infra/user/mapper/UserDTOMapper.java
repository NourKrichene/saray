package com.nour.saray.infra.user.mapper;


import com.nour.saray.domain.model.User;
import com.nour.saray.infra.user.model.UserDTO;

public final class UserDTOMapper {

    private UserDTOMapper() {
    }

    public static User toDomain(final UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return new User(userDTO.id(),userDTO.email(), userDTO.creationDate(),
                userDTO.tasks() == null ? null :
                userDTO.tasks().stream().map(TaskDTOMapper::toDomain).toList());
    }

    public static UserDTO toUser(final User user) {
        if (user == null) {
            return null;
        }
            return new UserDTO(user.id(),user.email(), user.creationDate(),
                    user.tasks() == null ? null :
                    user.tasks().stream().map(TaskDTOMapper::toUser).toList());
    }
}
