package org.openmrs.module.helloworld.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.helloworld.HelloWorldMessage;
import org.openmrs.module.helloworld.api.HelloWorldMessageService;
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
}