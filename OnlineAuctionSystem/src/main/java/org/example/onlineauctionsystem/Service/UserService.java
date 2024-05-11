package org.example.onlineauctionsystem.Service;

import org.example.onlineauctionsystem.Entity.User;
import org.example.onlineauctionsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null; // Authentication failed
        }
    }
    public User signUp(User user) {
        User usern = new User();
        usern.setUsername(user.getUsername());
        usern.setEmail(user.getEmail());
        usern.setPassword(user.getPassword());
        usern.setRole(user.getRole());

        // Set other user details

        userRepository.save(usern);
        return usern;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
