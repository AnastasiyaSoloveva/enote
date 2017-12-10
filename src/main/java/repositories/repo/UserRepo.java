package repositories.repo;

import entities.User;

import java.util.List;
import java.util.Set;

public interface UserRepo {

    List findAll();

    User findById(Long id);

    List<User> findAllByEmail(String email, boolean exactMatch);

    String findEmailById(Long id);

    long countUsers();

    void save(User user);

    void updatePassword(Long userId, String newPass);

    void deleteById(Long userId);

    void save(Set<User> users);
}
