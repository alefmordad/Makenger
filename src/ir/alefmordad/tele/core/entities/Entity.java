package ir.alefmordad.tele.core.entities;

import java.io.Serializable;

public abstract class Entity<I> implements Serializable {

    I id;

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }
}
