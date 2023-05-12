package service;

import dao.RecipeDAO;
import entity.Recipe;
import jdk.jshell.spi.ExecutionControl;
import util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecipeService {

    private RecipeDAO recipeDAO;
    private Connection connection;

    public RecipeService() {
        try {
            connection = new DataBaseManager().getConnection();
            recipeDAO = new RecipeDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean addRecipe(String name, String ingredient, String instruction) {
        Recipe recipe = new Recipe(name, ingredient, instruction);
        try {
            if(recipeDAO.save(recipe)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateRecipe(Recipe recipe) {
        try {
            if(recipeDAO.update(recipe)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Recipe getRecipe(int id) {
        try {
            return recipeDAO.getById(id);
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteRecipe(int id) {
        Recipe recipe = null;
        try {
            recipe = recipeDAO.getById(id);
            if(recipe != null) {
                return recipeDAO.delete(recipe);
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Recipe> getAllRecipes() {
        try {
            return recipeDAO.getAll();
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
