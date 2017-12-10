package repositories;

import entities.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repositories.repo.TagRepo;

import java.util.List;
import java.util.Set;

@Repository
public class HibernateTagRepo  implements TagRepo{


    @Autowired
    private SessionFactory sessionFactory;

    public HibernateTagRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List findAll() {
        return session().createQuery("from Tag t").list();
    }

    @Override
    public Tag findById(Long id) {
        return session().get(Tag.class, id);
    }

    @Override
    public List<Tag> findAllByName(String name, boolean exactMatch) {
        if (exactMatch) {
            return session().createQuery("from Tag t where name = ?")
                    .setParameter(0, name).list();
        } else {
            return session().createQuery("from Tag t where name like ?")
                    .setParameter(0, "%" + name + "%").list();
        }
    }

    @Override
    public String findNameById(Long id) {
        return (String) session().createQuery("select t.name from Tag t where t.id= :id").
                setParameter("id", id).uniqueResult();
    }

    @Override
    public long countTags() {
        return (Long) session()
                .createQuery("select count(t) from Tag t").uniqueResult();
    }

    @Override
    public void save(Tag tag) {
        session().persist(tag);

    }



    @Override
    public void deleteById(Long tagId) {
        Tag tag = (Tag) session().createQuery("from Tag t where t.id = :id").
                setParameter("id", tagId).uniqueResult();
        session().delete(tag);

    }

    @Override
    public void save(Set<Tag> tags) {
        for (Tag tag : tags) {
            session().persist(tag);
        }

    }
}
