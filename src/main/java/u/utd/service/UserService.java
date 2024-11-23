package u.utd.service;

import u.utd.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    void deleteUser(Long id);
}