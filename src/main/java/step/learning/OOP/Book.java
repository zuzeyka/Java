package step.learning.OOP;

public class Book extends Literature implements Copyable, Printable, Multiple{
    public Book(String author, String title, int count) {
        this.author = author;
        this.count = count;
        super.setTitle(title);
    }
    private int count;
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

    @Override
    public int getCount() {
        return count;
    }
}
