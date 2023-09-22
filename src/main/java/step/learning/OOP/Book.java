package step.learning.OOP;

public class Book extends Literature{
    public Book(String author, String title) {
        this.author = author;
        super.setTitle(title);
    }

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getCard() {
        return String.format("Book: %s '%s' ",
                getAuthor(),
                getTitle());
    }
}
