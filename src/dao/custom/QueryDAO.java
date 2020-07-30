package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    CustomEntity getOrderDetail(String orderId);
    CustomEntity getOrderDetail2(String orderId);
    List<CustomEntity> searchOrder();

}
