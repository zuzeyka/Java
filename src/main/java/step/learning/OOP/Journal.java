package step.learning.OOP;

import com.google.gson.JsonObject;

import java.text.ParseException;

@Serializable
public class Journal extends Literature implements Copyable, Periodic, Printable, Multiple {
    public Journal(String title, int number, int count) {
        this.count = count;
        this.setNumber(number);
        super.setTitle(title);
    }

    public Journal(String title, int number) {
        this.count = 1;
        this.setNumber(number);
        super.setTitle(title);
    }

    @ParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject){
        String[] requiredFields = {"title", "number"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }

    @FromJsonParser
    public static Journal fromJson(JsonObject jsonObject) throws ParseException {
        String[] requiredFields = {"title", "number"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field, 0);
            }
        }

        return new Journal(
                jsonObject.get(requiredFields[0]).getAsString(),
                jsonObject.get(requiredFields[1]).getAsInt()
        );
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;
    private int count;


    @Override
    public String getCard() {
        return String.format(
                "Journal '%s' №%s",
                super.getTitle(),
                this.getNumber()
        );
    }

    @Override
    public String getPeriod() {
        return "daily";
    }

    @Override
    public int getCount() {
        return count;
    }
}
