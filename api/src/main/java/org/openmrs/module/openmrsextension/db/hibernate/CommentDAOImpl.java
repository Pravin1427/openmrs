package org.openmrs.module.openmrsextension.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.openmrs.module.openmrsextension.Comment;
import org.openmrs.module.openmrsextension.db.CommentDAO;

import java.util.List;

public class CommentDAOImpl implements CommentDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Comment saveOrUpdate(Comment comment) {
        sessionFactory.getCurrentSession().saveOrUpdate(comment);
        return comment;
    }

    @Override
    public Comment getById(Integer id) {
        return sessionFactory.getCurrentSession().get(Comment.class, id);
    }

    @Override
    public Comment getByUuid(String uuid) {
        String hql = "from Comment c where c.uuid = :uuid";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Comment.class)
                .setParameter("uuid", uuid)
                .uniqueResult();
    }

    @Override
    public List<Comment> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Comment", Comment.class)
                .list();
    }

    @Override
    public void delete(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
    }

    @Override
    public List<Comment> getCommentsByMessageId(Integer messageId) {
        String hql = "from Comment c where c.message.id = :messageId";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Comment.class)
                .setParameter("messageId", messageId)
                .list();
    }
}
