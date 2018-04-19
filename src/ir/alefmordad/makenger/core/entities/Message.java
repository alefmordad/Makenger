package ir.alefmordad.makenger.core.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message extends Entity<Long> {

    private User source;
    private User destination;
    private String content;
    private Date date;
    private Boolean sent = false;
    private Boolean received = false;
    private Boolean seen = false;
    private Boolean visibleForMe = true;
    private Message replyTo;
    private User forwardFrom;

    public Boolean getVisibleForMe() {
        return visibleForMe;
    }

    public void setVisibleForMe(Boolean visibleForMe) {
        this.visibleForMe = visibleForMe;
    }

    public Message getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Message replyTo) {
        this.replyTo = replyTo;
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public void setForwardFrom(User forwardFrom) {
        this.forwardFrom = forwardFrom;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String message = "(" + sdf.format(date) + ") ";
        if (getReplyTo() != null) {
            message.concat(" reply to :" + getReplyTo().getSource().getId() + " : " + getReplyTo().getContent());
            message.concat(" " + source.getId() + " : " + content);
        } else if (getForwardFrom() != null)
            message.concat(" forward message from " + getForwardFrom().getId() + " : " + content);
        else
            message.concat(source.getId() + " : " + content);
        return message;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
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
