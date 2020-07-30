package dao.custom.impl;

import dao.custom.OrderDetailDAO;
import db.DBConnection;
import entity.OrderDetail;
import entity.OrderDetailPK;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public List<OrderDetail> findAll() {
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

    @Override
    public OrderDetail find(OrderDetailPK pk) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM OrderDetail WHERE orderId = ?");
            pstm.setObject(1,pk.getOrderId());
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

    @Override
    public boolean save(OrderDetail entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO OrderDetail VALUES (?,?,?,?)");
            pstm.setObject(1,entity.getOrderDetailPK().getOrderId());
            pstm.setObject(2,entity.getOrderDetailPK().getItemCode());
            pstm.setObject(3,entity.getQty());
            pstm.setObject(4,entity.getUnitPrice());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OrderDetail entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE OrderDetail SET itemCode=?, qty=?, unitPrice=? WHERE orderId=?");
            pstm.setObject(4,entity.getOrderDetailPK().getOrderId());
            pstm.setObject(1,entity.getOrderDetailPK().getItemCode());
            pstm.setObject(2,entity.getQty());
            pstm.setObject(3,entity.getUnitPrice());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OrderDetailPK pk) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM OrderDetail WHERE orderId=?");
            pstm.setObject(1,pk.getOrderId());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
