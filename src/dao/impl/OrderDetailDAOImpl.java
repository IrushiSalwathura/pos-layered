package dao.impl;

import dao.OrderDetailDAO;
import db.DBConnection;
import entity.Order;
import entity.OrderDetail;
import entity.OrderDetailPK;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    public List<Object> findAll(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM OrderDetail");
            ArrayList<Object> orderDetails = new ArrayList<>();
            while(rst.next()){
                orderDetails.add(new OrderDetail(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getBigDecimal(4)));
            }
            return orderDetails;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Object find(Object compositeKey){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM OrderDetail WHERE orderId = ?");
            OrderDetailPK entity = new OrderDetailPK();
            pstm.setObject(1,entity.getOrderId());
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

    public boolean save(Object entity){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO OrderDetail VALUES (?,?,?,?)");
            OrderDetail order = (OrderDetail) entity;
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

    public boolean update(Object entity){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE OrderDetail SET itemCode=?, qty=?, unitPrice=? WHERE orderId=?");
            OrderDetail order = (OrderDetail) entity;
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

    public boolean delete(Object compositeKey){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM OrderDetail WHERE orderId=?");
            OrderDetailPK orderDetailPK = new OrderDetailPK();
            pstm.setObject(1,orderDetailPK.getOrderId());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
