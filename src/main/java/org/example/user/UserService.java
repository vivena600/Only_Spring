package org.example.user;

import java.util.List;

interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
}
