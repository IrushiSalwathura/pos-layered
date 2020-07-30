package dao.custom;

import dao.CrudDAO;
import entity.Order;

import java.util.List;

public interface OrderDAO extends CrudDAO<Order,String> {
    public String getLastOrderId();
}
