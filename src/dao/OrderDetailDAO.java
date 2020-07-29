package dao;

import entity.OrderDetail;
import entity.OrderDetailPK;

import java.util.List;

public interface OrderDetailDAO {

    public List<OrderDetail> findAllOrderDetails();
    public OrderDetail findOrderDetail(OrderDetailPK orderDetailPK);
    public boolean saveOrderDetail(OrderDetail order);
    public boolean updateOrderDetail(OrderDetail order);
    public boolean deleteOrderDetail(OrderDetailPK orderDetailPK);
}
