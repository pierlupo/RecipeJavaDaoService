package service;

import dao.IngredientDAO;
import entity.Ingredient;
import jdk.jshell.spi.ExecutionControl;
import util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class IngredientService {

    private IngredientDAO ingredientDAO;
    private Connection connection;

    public IngredientService() {
        try {
            connection = new DataBaseManager().getConnection();
            ingredientDAO = new IngredientDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addIngredient(String name) {
        Ingredient ingredient = new Ingredient(name);
        try {
            if(ingredientDAO.save(ingredient)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateIngredient(Ingredient ingredient) {
        try {
            if(ingredientDAO.update(ingredient)) {
                return true;
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Ingredient getIngredient(int id) {
        try {
            return ingredientDAO.getById(id);
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteIngredient(int id) {
        Ingredient ingredient = null;
        try {
            ingredient = ingredientDAO.getById(id);
            if(ingredient != null) {
                return ingredientDAO.delete(ingredient);
            }
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Ingredient> getAllIngredients() {
        try {
            return ingredientDAO.getAll();
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

}
