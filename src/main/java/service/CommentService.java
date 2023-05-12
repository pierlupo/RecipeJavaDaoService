package service;

import dao.CommentDAO;
import entity.Comment;
import jdk.jshell.spi.ExecutionControl;
import util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommentService {

    private CommentDAO commentDAO;
    private Connection connection;

    public CommentService() {
        try {
            connection = new DataBaseManager().getConnection();
            commentDAO = new CommentDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean addComment(String comment) {
        Comment comments = new Comment(comment);
        try {
            if(commentDAO.save(comments)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateComment(Comment comment) {
        try {
            if(commentDAO.update(comment)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Comment getComment(int id) {
        try {
            return commentDAO.getById(id);
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteComment(int id) {
        Comment comment = null;
        try {
            comment = commentDAO.getById(id);
            if(comment != null) {
                return commentDAO.delete(comment);
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Comment> getAllComments() {
        try {
            return commentDAO.getAll();
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
