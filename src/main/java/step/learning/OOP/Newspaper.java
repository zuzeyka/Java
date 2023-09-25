package step.learning.OOP;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Newspaper extends Literature implements Periodic, Printable, Hologram{
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
