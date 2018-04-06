package ir.alefmordad.makenger.core.exceptions;

import ir.alefmordad.makenger.core.entities.User;

public class FakeUserException extends RuntimeException {

    private User user;

    public FakeUserException(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FakeUserException{" +
                "user=" + user +
                '}';
    }
}
