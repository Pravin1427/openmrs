package org.openmrs.module.openmrsextension.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.openmrsextension.Comment;
import org.openmrs.module.openmrsextension.api.CommentService;
import org.openmrs.module.openmrsextension.db.CommentDAO;

import java.util.List;

public class CommentServiceImpl extends BaseOpenmrsService implements CommentService {

    private CommentDAO commentDAO;

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentDAO.saveOrUpdate(comment);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentDAO.getById(id);
    }

    @Override
    public Comment getCommentByUuid(String uuid) {
        return commentDAO.getByUuid(uuid);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDAO.getAll();
    }

    @Override
    public void deleteComment(Comment comment) {
        commentDAO.delete(comment);
    }

    @Override
    public List<Comment> getCommentsByMessageId(Integer messageId) {
        return commentDAO.getCommentsByMessageId(messageId);
    }

    @Override
    public void purgeComment(Comment comment) {
        commentDAO.delete(comment); // using the same delete method for purge
    }

}
