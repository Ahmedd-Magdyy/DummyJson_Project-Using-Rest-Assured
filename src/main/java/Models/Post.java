package Models;

public class Post {

    private String title;
    private int userId;

    public Post() {
    }

    public Post(String title, int userId) {
        this.title = title;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
