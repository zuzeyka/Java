package step.learning.OOP;

import com.google.gson.JsonObject;

import java.text.ParseException;

@Serializable
public class Book extends Literature implements Copyable, Printable, Multiple{
    public Book(String author, String title, int count) {
        this.author = author;
        this.count = count;
        super.setTitle(title);
    }

    public Book(String author, String title) {
        this.author = author;
        this.count = 1;
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

    @FromJsonParser
    public static Book fromJson(JsonObject jsonObject) throws ParseException{
        String[] requiredFields = {"author", "title"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field, 0);
            }
        }

        return new Book(
                jsonObject.get(requiredFields[0]).getAsString(),
                jsonObject.get(requiredFields[1]).getAsString()
        );
    }

    @ParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject){
        String[] requiredFields = {"author", "title"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
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
