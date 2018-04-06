package ir.alefmordad.tele.util;

import java.io.Serializable;

public class User implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
