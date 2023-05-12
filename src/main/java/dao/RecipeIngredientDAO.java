package dao;

import entity.RecipeIngredient;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientDAO extends BaseDAO<RecipeIngredient>{
    public RecipeIngredientDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(RecipeIngredient recipeIngredient) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO ingredient_recipe (ingredient_id, recipe_id) values (?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, recipeIngredient.getIngredientId());
        statement.setInt(2, recipeIngredient.getRecipeId());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()) recipeIngredient.setId(resultSet.getInt(1));
        return nbRow == 1;
    }

    @Override
    public RecipeIngredient getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        RecipeIngredient recipeIngredient = null;
        request = "select * from ingredient_recipe where id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if(resultSet.next()) {
            recipeIngredient = new RecipeIngredient(resultSet.getInt("id"),
                    resultSet.getInt("ingredient_id"),
                    resultSet.getInt("recipe_id"));
        }
        return recipeIngredient;
    }

    @Override
    public List<RecipeIngredient> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        List<RecipeIngredient> result = new ArrayList<>();
        request = "select * from ingredient_recipe";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while(resultSet.next()){
            RecipeIngredient recipeIngredient = new RecipeIngredient(resultSet.getInt("id"),
                    resultSet.getInt("ingredient_id"),
                    resultSet.getInt("recipe_id"));
            result.add(recipeIngredient);
        }
        return result;
    }

    @Override
    public boolean update(RecipeIngredient recipeIngredient) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE ingredient_recipe set ingredient_id = ?, recipe_id = ? where id = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1,recipeIngredient.getIngredientId());
        statement.setInt(2,recipeIngredient.getRecipeId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }

    @Override
    public boolean delete(RecipeIngredient recipeIngredient) throws ExecutionControl.NotImplementedException, SQLException {
        request = "delete from ingredient_recipe where id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, recipeIngredient.getId());
        int nbRow = statement.executeUpdate();
        return nbRow ==1;
    }
}
