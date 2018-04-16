package ir.alefmordad.makenger.core.service;

import ir.alefmordad.makenger.core.entities.Entity;
import ir.alefmordad.makenger.core.manager.Manager;

import java.sql.SQLException;

public abstract class Service<T extends Entity<I>, I> {

    protected Manager<T, I> manager;

    public Manager<T, I> getManager() {
        return manager;
    }

    public void setManager(Manager<T, I> manager) {
        this.manager = manager;
    }

    public void create(T entity) throws SQLException {
        manager.create(entity);
    }

    public T read(I id) throws SQLException {
        return manager.read(id);
    }

    public void update(T entity) throws SQLException {
        manager.update(entity);
    }

    public void delete(I id) throws SQLException {
        manager.delete(id);
    }
}
