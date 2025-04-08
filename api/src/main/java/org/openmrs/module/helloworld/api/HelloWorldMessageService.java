package org.openmrs.module.helloworld.api;

import org.openmrs.module.helloworld.HelloWorldMessage;
import java.util.List;

public interface HelloWorldMessageService {

    HelloWorldMessage getMessage(Integer id);

    HelloWorldMessage getMessageByUuid(String uuid);

    HelloWorldMessage saveMessage(HelloWorldMessage message);

    List<HelloWorldMessage> getAllMessages();

    List<HelloWorldMessage> searchMessages(String query);

    void voidMessage(HelloWorldMessage message, String reason);

    void purgeMessage(HelloWorldMessage message);
}