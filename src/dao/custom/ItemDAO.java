package dao.custom;

import dao.CrudDAO;
import entity.Item;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
    public String getLastItemCode();

}
