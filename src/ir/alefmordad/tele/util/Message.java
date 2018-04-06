package ir.alefmordad.tele.util;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private User source;
    private User destination;
    private String content;
    private Date date;

    @Override
    public String toString() {
       return date.toString() + "\n" + source.getId() + " : " + content;
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
