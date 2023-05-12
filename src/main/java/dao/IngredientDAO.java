package dao;

import entity.Comment;
import entity.Ingredient;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO extends BaseDAO<Ingredient>{


    public IngredientDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Ingredient ingredient) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO ingredient (name) values (?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, ingredient.getName());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()) ingredient.setId(resultSet.getInt(1));
        return nbRow == 1;
    }

    @Override
    public Ingredient getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Ingredient ingredient = null;
        request = "select * from ingredient where id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if(resultSet.next()) {
            ingredient = new Ingredient(resultSet.getInt("id"),
                    resultSet.getString("name"));
        }
        return ingredient;
    }

    @Override
    public List<Ingredient> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        List<Ingredient> result = new ArrayList<>();
        request = "select * from ingredient";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while(resultSet.next()){
            Ingredient ingredient = new Ingredient(resultSet.getInt("id"),
                    resultSet.getString("name"));

            result.add(ingredient);
        }
        return result;    }

    @Override
    public boolean update(Ingredient ingredient) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE ingredient set name = ? where id = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,ingredient.getName());
        statement.setInt(2,ingredient.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }

    @Override
    public boolean delete(Ingredient ingredient) throws ExecutionControl.NotImplementedException, SQLException {
        request = "delete from ingredient where id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, ingredient.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }
}
