package repositories.impl;

import entities.Notebook;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repositories.NotebookRepo;

import java.util.List;
import java.util.Set;

@Repository("NotebookRepo")
@Transactional
public class HibernateNotebookRepo implements NotebookRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public HibernateNotebookRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Notebook findById(Long id) {
        return session().get(Notebook.class, id);
    }

    @Override
    public List findAllNotes() {
        return session().createQuery("from Notebook n").list();
    }

    @Override
    public void updateName(Long notebookId, String newName) {
        Notebook nb = (Notebook) session().createQuery("from Notebook n where n.id = :id").
                setParameter("id", notebookId).uniqueResult();
        nb.setName(newName);
        session().update(nb);
    }

    @Override
    public void deleteById(Long notebookId) {
        Notebook nb = (Notebook) session().createQuery("from Notebook n where n.id = :id").
                setParameter("id", notebookId).uniqueResult();
        session().delete(nb);
    }


    @Override
    public void save(Notebook nb) {
        session().persist(nb);
    }

    @Override
    public User findUserByNotebookId(Long id) {
        return (User)session().createQuery("select nb.user from Notebook nb where nb.id = :id")
                .setParameter("id", id).uniqueResult();
    }
}
