package entity;

public class Comment {
    private int id;
    private String comment;
    private int recipeCommentedId;

    public Comment(int id, String comment, int recipeCommentedId) {
        this(comment, recipeCommentedId);
        this.id = id;
    }


    public Comment(String comment, int recipeCommentedId) {
        this.comment = comment;
        this.recipeCommentedId = recipeCommentedId;
    }

    public Comment(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRecipeCommentedId() {
        return recipeCommentedId;
    }

    public void setRecipeCommentedId(int recipeCommentedId) {
        this.recipeCommentedId = recipeCommentedId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", recipeCommentedId=" + recipeCommentedId +
                '}';
    }
}
