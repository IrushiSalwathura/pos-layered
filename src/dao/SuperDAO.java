package dao;

import java.io.Serializable;
import java.util.List;

public interface SuperDAO<T extends Serializable,ID extends Serializable> {
    List<T> findAll();
    T find(ID pk);
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(ID pk);
}
