package step.learning.OOP;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Serializable
public class Newspaper extends Literature implements Periodic, Printable {
    private Date date;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public String getCard() {
        return String.format(
                "Newspaper: '%s' from %s",
                super.getTitle(),
                dateFormat.format(this.getDate())
        );
    }

    @ParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject){
        String[] requiredFields = {"title", "date"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }

    @FromJsonParser
    public static Newspaper fromJson(JsonObject jsonObject) throws ParseException{
        String[] requiredFields = {"title", "date"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field, 0);
            }
        }

        return new Newspaper(
                jsonObject.get(requiredFields[0]).getAsString(),
                jsonObject.get(requiredFields[1]).getAsString()
        );
    }

    public Newspaper(String title, Date date){
        super.setTitle(title);
        this.setDate(date);
    }

    public Newspaper(String title, String date) throws ParseException {
        this(title, dateFormat.parse(date));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getPeriod() {
        return "daily";
    }
}
