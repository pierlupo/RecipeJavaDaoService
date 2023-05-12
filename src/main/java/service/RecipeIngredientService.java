package service;

import dao.RecipeIngredientDAO;
import entity.RecipeIngredient;
import jdk.jshell.spi.ExecutionControl;
import util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecipeIngredientService {

    private RecipeIngredientDAO recipeIngredientDAO;
    private Connection connection;

    public RecipeIngredientService() {
        try {
            connection = new DataBaseManager().getConnection();
            recipeIngredientDAO = new RecipeIngredientDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean addRecipeIngredient(int recipeId, int ingredientId) {
        RecipeIngredient recipeIngredient = new RecipeIngredient(recipeId, ingredientId);
        try {
            if(recipeIngredientDAO.save(recipeIngredient)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateRecipeIngredient(RecipeIngredient recipeIngredient) {
        try {
            if(recipeIngredientDAO.update(recipeIngredient)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public RecipeIngredient getRecipeIngredient(int id) {
        try {
            return recipeIngredientDAO.getById(id);
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteRecipeIngredient(int id) {
        RecipeIngredient recipeIngredient = null;
        try {
            recipeIngredient = recipeIngredientDAO.getById(id);
            if(recipeIngredient != null) {
                return recipeIngredientDAO.delete(recipeIngredient);
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<RecipeIngredient> getAllRecipeIngredients() {
        try {
            return recipeIngredientDAO.getAll();
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
