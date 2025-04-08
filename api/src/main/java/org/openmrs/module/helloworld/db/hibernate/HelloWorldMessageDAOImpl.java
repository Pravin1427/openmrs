package org.openmrs.module.helloworld.db.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.HibernateOpenmrsObjectDAO;
import org.openmrs.module.helloworld.HelloWorldMessage;
import org.openmrs.module.helloworld.db.HelloWorldMessageDAO;
import java.util.List;

public class HelloWorldMessageDAOImpl extends HibernateOpenmrsObjectDAO<HelloWorldMessage>
        implements HelloWorldMessageDAO {

    @Override
    public HelloWorldMessage getMessage(Integer id) {
        return getByUuid(id.toString()); // Using parent class method
    }

    @Override
    public HelloWorldMessage getMessageByUuid(String uuid) {
        return getByUuid(uuid); // Using parent class method
    }

    @Override
    public HelloWorldMessage saveMessage(HelloWorldMessage message) {
        return saveOrUpdate(message); // Using parent class method
    }

    @Override
    public List<HelloWorldMessage> getAllMessages() {
        Criteria crit = sessionFactory.getCurrentSession()
                .createCriteria(HelloWorldMessage.class)
                .add(Restrictions.eq("voided", false));
        return crit.list();
    }

    @Override
    public List<HelloWorldMessage> getMessagesBySearch(String query) {
        return sessionFactory.getCurrentSession()
                .createCriteria(HelloWorldMessage.class)
                .add(Restrictions.ilike("message", "%" + query + "%"))
                .add(Restrictions.eq("voided", false))
                .list();
    }

    @Override
    public void purgeMessage(HelloWorldMessage message) {
        delete(message); // Using parent class method
    }
}