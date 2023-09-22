package step.learning.OOP;

public abstract class Literature {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract String getCard();
}
