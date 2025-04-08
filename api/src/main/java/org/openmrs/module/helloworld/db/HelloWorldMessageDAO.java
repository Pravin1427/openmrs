package org.openmrs.module.helloworld.db;

import org.openmrs.module.helloworld.HelloWorldMessage;
import java.util.List;

public interface HelloWorldMessageDAO {

    HelloWorldMessage getMessage(Integer id);

    HelloWorldMessage getMessageByUuid(String uuid);

    HelloWorldMessage saveMessage(HelloWorldMessage message);

    List<HelloWorldMessage> getAllMessages();

    List<HelloWorldMessage> getMessagesBySearch(String query);

    void purgeMessage(HelloWorldMessage message);
}