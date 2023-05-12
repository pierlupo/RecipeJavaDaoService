package dao;

import entity.Comment;
import entity.Recipe;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends BaseDAO<Comment>{
    public CommentDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Comment comment) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO comment (comment) values (?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, comment.getComment());
        statement.setInt(2, comment.getRecipeCommentedId());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()) comment.setId(resultSet.getInt(1));
        return nbRow == 1;
    }

    @Override
    public Comment getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Comment comment = null;
        request = "select * from comment where id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if(resultSet.next()) {
            comment = new Comment(resultSet.getInt("id"),
                    resultSet.getString("comment"),
                    resultSet.getInt("recipe_commented_id"));
        }
        return comment;
    }

    @Override
    public List<Comment> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        List<Comment> result = new ArrayList<>();
        request = "select * from comment";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while(resultSet.next()){
            Comment comment = new Comment(resultSet.getInt("id"),
                    resultSet.getString("comment"),
                    resultSet.getInt("recipe_commented_id"));
            result.add(comment);
        }
        return result;
    }

    @Override
    public boolean update(Comment comment) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE comment set comment = ? where id = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,comment.getComment());
        statement.setInt(2,comment.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }

    @Override
    public boolean delete(Comment comment) throws ExecutionControl.NotImplementedException, SQLException {
        request = "delete from comment where id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, comment.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }
}
