package org.openmrs.module.helloworld.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.helloworld.HelloWorldMessage;
import org.openmrs.module.helloworld.api.HelloWorldMessageService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.springframework.web.bind.annotation.RequestMethod;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;


import java.util.Date;
import java.util.UUID;

@Resource(
        name = RestConstants.VERSION_1 + "/helloworld",
        supportedClass = HelloWorldMessage.class,
        supportedOpenmrsVersions = {"2.7.*"}


)
public class HelloWorldMessageResource extends DelegatingCrudResource<HelloWorldMessage> {

    @Override
    public HelloWorldMessage getByUniqueId(String uuid) {
        if (uuid == null) {
            return null;
        }
        return getService().getMessageByUuid(uuid);
    }

    @Override
    public HelloWorldMessage save(HelloWorldMessage message) {
        // Set required fields if this is a new message
        if (message.getId() == null) {
            message.setCreator(Context.getAuthenticatedUser());
            message.setDateCreated(new Date());
            message.setUuid(UUID.randomUUID().toString());
            message.setVoided(false);
        }
        return getService().saveMessage(message);
    }

    @Override
    public HelloWorldMessage newDelegate() {
        return new HelloWorldMessage();
    }

    @Override
    public void purge(HelloWorldMessage message, RequestContext context) {
        getService().purgeMessage(message);
    }

    @Override
    public void delete(HelloWorldMessage message, String reason, RequestContext context) {
        if (message == null) {
            throw new IllegalArgumentException("Cannot delete null message");
        }
        getService().voidMessage(message, reason);
    }


    @Override
    public String getUniqueId(HelloWorldMessage message) {
        return message.getUuid();
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription description = new DelegatingResourceDescription();

        if (rep instanceof DefaultRepresentation) {
            description.addProperty("uuid");
            description.addProperty("message");
            description.addProperty("creator");
            description.addProperty("dateCreated");
            description.addProperty("voided");
            description.addProperty("voidReason");
        } else if (rep instanceof RefRepresentation) {
            description.addProperty("uuid");
            description.addProperty("message");
        }

        return description;
    }
    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("message");
        // Add other properties that should be settable during creation
        description.addProperty("voidReason");
        return description;
    }


    @Override
    public NeedsPaging<HelloWorldMessage> doGetAll(RequestContext context) {
        return new NeedsPaging<>(getService().getAllMessages(), context);
    }

    private HelloWorldMessageService getService() {
        return Context.getService(HelloWorldMessageService.class);
    }

    @Override
    public DelegatingResourceDescription getUpdatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addProperty("message");
        description.addProperty("voided");
        description.addProperty("voidReason");
        return description;
    }

    @PropertySetter("message")
    public void setMessage(HelloWorldMessage instance, String message) {
        instance.setMessage(message);
    }

    @PropertySetter("voided")
    public void setVoided(HelloWorldMessage instance, Boolean voided) {
        instance.setVoided(voided);
    }

    @PropertySetter("voidReason")
    public void setVoidReason(HelloWorldMessage instance, String reason) {
        instance.setVoidReason(reason);
    }

    @PropertySetter("uuid")
    public void setUuid(HelloWorldMessage instance, String uuid) {
        instance.setUuid(uuid);
    }
    @Override
    public HelloWorldMessage update(String uuid, SimpleObject propertiesToUpdate, RequestContext context) {
        HelloWorldMessage existing = getByUniqueId(uuid);
        if (existing == null) {
            throw new IllegalArgumentException("Message not found for uuid: " + uuid);
        }

        if (propertiesToUpdate.containsKey("message")) {
            existing.setMessage((String) propertiesToUpdate.get("message"));
        }

        // If you want to support voiding through update as well (optional)
        if (propertiesToUpdate.containsKey("voided")) {
            existing.setVoided((Boolean) propertiesToUpdate.get("voided"));
        }
        if (propertiesToUpdate.containsKey("voidReason")) {
            existing.setVoidReason((String) propertiesToUpdate.get("voidReason"));
        }

        return getService().saveMessage(existing);
    }

}
