package dao;

import entity.Item;

import java.util.List;

public interface SuperDAO {
    List<Object> findAll();
    Object find(Object pk);
    boolean save(Object entity);
    boolean update(Object entity);
    boolean delete(Object pk);
}
