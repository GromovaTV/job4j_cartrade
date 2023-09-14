package repository;

import model.User;

public interface UserStore {
    User findUserById(int id);
}
