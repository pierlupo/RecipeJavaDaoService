package entity;

public class Recipe {

    private int id;
    private String name;
    private String ingredient;
    private String instruction;

    public Recipe(String name, String ingredient, String instruction) {
        this.name = name;
        this.ingredient = ingredient;
        this.instruction = instruction;
    }

    public Recipe(int id, String name, String ingredient, String instruction) {
        this(name, ingredient, instruction);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
