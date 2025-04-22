package org.openmrs.module.openmrsextension.web.resource;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.openmrsextension.Comment;
import org.openmrs.module.openmrsextension.api.CommentService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
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
        name = RestConstants.VERSION_1 + "/comment",
        supportedClass = Comment.class,
        supportedOpenmrsVersions = {"2.7.*"}
)
public class CommentResource extends DelegatingCrudResource<Comment> {

    @Override
    public Comment getByUniqueId(String uuid) {
        return getService().getCommentByUuid(uuid);
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            comment.setCreator(Context.getAuthenticatedUser());
            comment.setDateCreated(new Date());
            comment.setUuid(UUID.randomUUID().toString());
        }
        return getService().saveComment(comment);
    }

    @Override
    public Comment newDelegate() {
        return new Comment();
    }

    @Override
    public void purge(Comment comment, RequestContext context) {
        getService().purgeComment(comment);
    }

    @Override
    public void delete(Comment comment, String reason, RequestContext context) {
        // Optional: You can soft-delete (void) here if desired
        purge(comment, context);
    }

    @Override
    public String getUniqueId(Comment comment) {
        return comment.getUuid();
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        if (rep instanceof DefaultRepresentation) {
            description.addProperty("uuid");
            description.addProperty("text");
            description.addProperty("creator");
            description.addProperty("dateCreated");
            description.addProperty("message"); // Link to HelloWorldMessage
        } else if (rep instanceof RefRepresentation) {
            description.addProperty("uuid");
            description.addProperty("text");
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("text");
        description.addRequiredProperty("message"); // required relationship
        return description;
    }

    @Override
    public DelegatingResourceDescription getUpdatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addProperty("text");
        return description;
    }

    @Override
    public NeedsPaging<Comment> doGetAll(RequestContext context) {
        return new NeedsPaging<>(getService().getAllComments(), context);
    }

    private CommentService getService() {
        return Context.getService(CommentService.class);
    }
}
