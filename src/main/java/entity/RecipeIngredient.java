package entity;

public class RecipeIngredient {

    private int id;
    private int recipeId;
    private int ingredientId;

    public RecipeIngredient(int id, int recipeId, int ingredientId) {
        this(recipeId,ingredientId);
        this.id = id;
    }

    public RecipeIngredient(int recipeId, int ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", ingredientId=" + ingredientId +
                '}';
    }
}
