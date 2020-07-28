package dao;

import db.DBConnection;
import entity.Order;
import entity.OrderDetail;
import entity.OrderDetailPK;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {
    public static List<OrderDetail> findAllOrderDetails(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM OrderDetail");
            ArrayList<OrderDetail> orderDetails = new ArrayList<>();
            while(rst.next()){
                orderDetails.add(new OrderDetail(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getBigDecimal(4)));
            }
            return orderDetails;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static OrderDetail findOrderDetail(OrderDetailPK orderDetailPK){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM OrderDetail WHERE orderId = ?");
            pstm.setObject(1,orderDetailPK.getOrderId());
            ResultSet rst = pstm.executeQuery();
            while(rst.next()){
                return new OrderDetail(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getBigDecimal(4));
            }
            return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static boolean saveOrderDetail(OrderDetail order){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO OrderDetail VALUES (?,?,?,?)");
            pstm.setObject(1,order.getOrderDetailPK().getOrderId());
            pstm.setObject(2,order.getOrderDetailPK().getItemCode());
            pstm.setObject(3,order.getQty());
            pstm.setObject(4,order.getUnitPrice());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean updateOrderDetail(OrderDetail order){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE OrderDetail SET itemCode=?, qty=?, unitPrice=? WHERE orderId=?");
            pstm.setObject(4,order.getOrderDetailPK().getOrderId());
            pstm.setObject(1,order.getOrderDetailPK().getItemCode());
            pstm.setObject(2,order.getQty());
            pstm.setObject(3,order.getUnitPrice());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean deleteOrderDetail(OrderDetailPK orderDetailPK){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM OrderDetail WHERE orderId=?");
            pstm.setObject(1,orderDetailPK.getOrderId());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
