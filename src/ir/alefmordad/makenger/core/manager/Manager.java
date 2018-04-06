package ir.alefmordad.makenger.core.manager;

import ir.alefmordad.makenger.core.dao.Dao;
import ir.alefmordad.makenger.core.entities.Entity;
import ir.alefmordad.makenger.core.entities.Message;

import java.sql.SQLException;
import java.util.List;

public abstract class Manager<T extends Entity<I>, I> {

    protected Dao<T, I> dao;

    public Dao<T, I> getDao() {
        return dao;
    }

    public void setDao(Dao<T, I> dao) {
        this.dao = dao;
    }

    public void create(T entity) throws SQLException {
        dao.create(entity);
    }

    public T read(I id) throws SQLException {
        return dao.read(id);
    }

    public void update(T entity) throws SQLException {
        dao.update(entity);
    }

    public void delete(I id) throws SQLException {
        dao.delete(id);
    }

}
