package com.nour.saray.infra.user;

import com.nour.saray.domain.ports.user.UserService;
import com.nour.saray.infra.user.mapper.UserDTOMapper;
import com.nour.saray.infra.user.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        UserDTO userDTO = UserDTOMapper.toUser(userService.getUserById(id));
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUserDTO = UserDTOMapper.toUser(userService.create(UserDTOMapper.toDomain(userDTO)));
        return ResponseEntity.ok(createdUserDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUserDTO = UserDTOMapper.toUser(userService.update(id, UserDTOMapper.toDomain(userDTO)));
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
