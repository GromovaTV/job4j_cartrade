package repository;

import model.User;
import org.hibernate.query.Query;

public class UserRepository extends CommonRepository implements UserStore {
    private static final class Lazy {
        private static UserStore INST;
    }

    private UserRepository() {
    }

    public static UserStore instOf() {
        if (Lazy.INST == null) {
            Lazy.INST = new UserRepository();
        }
        return Lazy.INST;
    }

    @Override
    public User findUserById(int id) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("select distinct u from User u " +
                            "where u.id =:id");
                    query.setParameter("id", id);
                    User user = (User) query.uniqueResult();
                    return user;
                }
        );
    }

    @Override
    public User findUserByEmail(String email) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("select distinct u from User u " +
                            "where u.email =:email");
                    query.setParameter("email", email);
                    User user = (User) query.uniqueResult();
                    return user;
                }
        );
    }

    @Override
    public void save(User user) {
        this.tx(
                session -> {
                    session.save(user);
                    return user;
                }
        );
    }
}
