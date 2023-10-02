package step.learning.OOP;

import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<String> getRequiredFieldsNames() {
        if (requiredFieldsNames == null){
            Field[] fields = Book.class.getDeclaredFields(); // .getFields(); - пустая коллекция
            Field[] fields2 = Book.class.getSuperclass().getDeclaredFields();
            requiredFieldsNames = Stream.concat(Arrays.stream(fields), Arrays.stream(fields2)).filter(field -> field.isAnnotationPresent(Required.class)).map(Field::getName).collect(Collectors.toList());
        }
        return requiredFieldsNames;
    }

    private static List<String> requiredFieldsNames;
    private int count;
    @Required
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
        // String[] requiredFields = {"author", "title"};


        for (String field : getRequiredFieldsNames()){
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
