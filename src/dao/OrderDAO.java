package dao;

import entity.Order;

import java.util.List;

public interface OrderDAO {
    public List<Order> findAllOrders();
    public Order findOrder(String id);
    public boolean saveOrder(Order order);
    public boolean updateOrder(Order order);
    public boolean deleteOrder(String id);
    public String getLastOrderId();
}
