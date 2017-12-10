package repositories;

import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repositories.repo.UserRepo;

import java.util.List;
import java.util.Set;


@Repository
@Transactional
public class HibernateUserRepo implements UserRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public HibernateUserRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<User> findAll() {
        return session().createQuery("from User u").list();
    }

    @Override
    public User findById(Long id) {
        return session().get(User.class, id);
    }

    @Override
    public List<User> findAllByEmail(String email, boolean exactMatch) {

        if (exactMatch) {
            return session().createQuery("from User u where email = ?")
                    .setParameter(0, email).list();
        } else {
            return session().createQuery("from User u where email like ?")
                    .setParameter(0, "%" + email + "%").list();
        }
    }

    @Override
    public String findEmailById(Long id) {
        return (String) session().createQuery("select u.username from User u where u.id= :id").
                setParameter("id", id).uniqueResult();
    }

    @Override
    public long countUsers() {
        return (Long) session()
                .createQuery("select count(u) from User u").uniqueResult();
    }

    @Override
    public void save(User user) {
        session().persist(user);
    }

    @Override
    public void updatePassword(Long userId, String newPass) {
        User user = (User) session().createQuery("from User u where u.id = :id").
                setParameter("id", userId).uniqueResult();
        user.setPassword(newPass);
        session().update(user);

    }

    @Override
    public void deleteById(Long userId) {
        User user = (User) session().createQuery("from User u where u.id = :id").
                setParameter("id", userId).uniqueResult();
        session().delete(user);

    }

    @Override
    public void save(Set<User> users) {
        for (User user : users) {
            session().persist(user);
        }

    }
}
