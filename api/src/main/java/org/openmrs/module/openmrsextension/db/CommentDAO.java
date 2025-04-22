package org.openmrs.module.openmrsextension.db;

import org.openmrs.module.openmrsextension.Comment;
import java.util.List;

public interface CommentDAO {
    Comment saveOrUpdate(Comment comment);
    Comment getById(Integer id);
    Comment getByUuid(String uuid);
    List<Comment> getAll();
    void delete(Comment comment);
    List<Comment> getCommentsByMessageId(Integer messageId);
}
