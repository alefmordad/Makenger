package ir.alefmordad.tele.core.manager;

import ir.alefmordad.tele.core.dao.Dao;
import ir.alefmordad.tele.core.entities.Entity;

import java.sql.SQLException;

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
