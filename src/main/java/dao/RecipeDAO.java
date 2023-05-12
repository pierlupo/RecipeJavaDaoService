package dao;

import entity.Recipe;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO extends BaseDAO<Recipe>{
    public RecipeDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Recipe recipe) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO recipe (name, ingredient, instruction) values (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, recipe.getName());
        statement.setString(2, recipe.getIngredient());
        statement.setString(3, recipe.getInstruction());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()) recipe.setId(resultSet.getInt(1));
        return nbRow == 1;
    }


    @Override
    public Recipe getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Recipe recipe = null;
        request = "select * from recipe where id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if(resultSet.next()) {
            recipe = new Recipe(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("ingredient"),
                    resultSet.getString("instruction"));
        }
        return recipe;
    }

    @Override
    public List<Recipe> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        List<Recipe> result = new ArrayList<>();
        request = "select * from recipe";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while(resultSet.next()){
            Recipe recipe = new Recipe(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("ingredient"),
                    resultSet.getString("instruction"));
            result.add(recipe);
        }
        return result;
    }

    @Override
    public boolean update(Recipe recipe) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE recipe set name = ?, ingredient = ?, instruction = ? where id = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,recipe.getName());
        statement.setString(2,recipe.getIngredient());
        statement.setString(3,recipe.getInstruction());
        statement.setInt(4,recipe.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }

    @Override
    public boolean delete(Recipe recipe) throws ExecutionControl.NotImplementedException, SQLException {
        request = "delete from recipe where id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, recipe.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }
}
