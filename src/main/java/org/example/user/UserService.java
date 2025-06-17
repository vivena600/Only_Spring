package org.example.user;

import java.util.List;

interface UserService {
    List<UserDto> getAllUsers();
    UserDto saveUser(UserDto userDto);
}
