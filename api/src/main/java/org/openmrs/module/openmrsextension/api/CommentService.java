package org.openmrs.module.openmrsextension.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.openmrsextension.Comment;

import java.util.List;

public interface CommentService extends OpenmrsService {

    Comment saveComment(Comment comment);

    Comment getCommentById(Integer id);

    Comment getCommentByUuid(String uuid);

    List<Comment> getAllComments();

    void deleteComment(Comment comment);

    List<Comment> getCommentsByMessageId(Integer messageId);

    void purgeComment(Comment comment);
}
