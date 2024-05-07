package org.example.onlineauctionsystem.Service;

import org.example.onlineauctionsystem.Entity.User;
import org.example.onlineauctionsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User signUp(User user) {
        User usern = new User();
        usern.setUsername(user.getUsername());
        usern.setEmail(user.getEmail());
        usern.setPassword(user.getPassword());
        // Set other user details

        userRepository.save(usern);
        return usern;
    }

}
