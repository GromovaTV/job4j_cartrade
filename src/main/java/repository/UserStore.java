package repository;

import model.User;

public interface UserStore {

    User findUserById(int id);

    User findUserByEmail(String email);

    void save(User user);
}
