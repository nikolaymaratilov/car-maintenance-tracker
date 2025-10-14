package app.user.service;

import app.user.model.User;
import app.user.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    @Transactional
    public User create(User user) {
        return users.save(user);
    }

    public Optional<User> getById(UUID id) {
        return users.findById(id);
    }

    public Optional<User> getByUsername(String username) {
        return users.findByUsername(username);
    }
}