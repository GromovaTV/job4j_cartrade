package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.function.Function;

public class CommonRepository implements AutoCloseable {
    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private static final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();


    protected <T> T create(T t) {
        return tx(session -> {
            session.save(t);
            return t;
        });
    }

    protected <T> void update(T t) {
        tx(session -> {
            session.update(t);
            return t;
        });
    }

    protected <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}

