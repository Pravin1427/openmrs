package org.openmrs.module.helloworld.api.impl;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.helloworld.HelloWorldMessage;
import org.openmrs.module.helloworld.api.HelloWorldMessageService;
import org.openmrs.module.helloworld.db.HelloWorldMessageDAO;
import java.util.Date;
import java.util.List;

public class HelloWorldMessageServiceImpl extends BaseOpenmrsService implements HelloWorldMessageService {

    private HelloWorldMessageDAO dao;

    public void setDao(HelloWorldMessageDAO dao) {
        this.dao = dao;
    }

    @Override
    public HelloWorldMessage getMessage(Integer id) {
        return dao.getMessage(id);
    }

    @Override
    public HelloWorldMessage getMessageByUuid(String uuid) {
        return dao.getMessageByUuid(uuid);
    }

    @Override
    public HelloWorldMessage saveMessage(HelloWorldMessage message) {
        if (message.getId() == null) {
            message.setCreator(Context.getAuthenticatedUser());
            message.setDateCreated(new Date());
            message.setUuid(java.util.UUID.randomUUID().toString());
        } else {
            message.setChangedBy(Context.getAuthenticatedUser());
            message.setDateChanged(new Date());
        }
        return dao.saveMessage(message);
    }

    @Override
    public List<HelloWorldMessage> getAllMessages() {
        return dao.getAllMessages();
    }

    @Override
    public List<HelloWorldMessage> searchMessages(String query) {
        return dao.getMessagesBySearch(query);
    }

    @Override
    public void voidMessage(HelloWorldMessage message, String reason) {
        message.setVoided(true);
        message.setVoidedBy(Context.getAuthenticatedUser());
        message.setDateVoided(new Date());
        message.setVoidReason(reason);
        dao.saveMessage(message);
    }

    @Override
    public void purgeMessage(HelloWorldMessage message) {
        dao.purgeMessage(message);
    }
}