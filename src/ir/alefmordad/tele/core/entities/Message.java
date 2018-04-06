package ir.alefmordad.tele.core.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message extends Entity<Integer> {

    private User source;
    private User destination;
    private String content;
    private Date date;
    private Boolean delivered;

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        return "(" + sdf.format(date) + ") " + source.getId() + " : " + content;
    }

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}