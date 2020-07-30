package dao.custom.impl;

import dao.custom.QueryDAO;
import db.DBConnection;
import entity.CustomEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public CustomEntity getOrderDetail(String orderId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT o.id,c.name,o.date FROM `Order` o\n" +
                    "INNER JOIN Customer c on o.customerId = c.id\n" +
                    "WHERE o.id = ?");
            pstm.setObject(1,orderId);
            ResultSet rst = pstm.executeQuery();
            if(rst.next()){
                return new CustomEntity(rst.getString(1),
                        rst.getString(2), rst.getDate(3));

            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public CustomEntity getOrderDetail2(String orderID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT o.id,c.name,c.id FROM Customer c\n" +
                    "INNER JOIN `Order` o on c.id = o.customerId\n" +
                    "WHERE o.id = ?");
            pstm.setObject(1,orderID);
            ResultSet rst = pstm.executeQuery();

            if(rst.next()){
                return new CustomEntity(rst.getString(1),rst.getString(2),
                        rst.getString(3));
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    @Override
    public List<CustomEntity> searchOrder() {
            try {
                ArrayList<CustomEntity> orders = new ArrayList<>();
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement("SELECT o.id,C.name,o.date,C.id,SUM(OD.qty*OD.unitPrice) AS 'Total' FROM `Order` O\n" +
                        "INNER JOIN Customer C on O.customerId = C.id\n" +
                        "INNER JOIN OrderDetail OD on O.id = OD.orderId\n" +
                        "GROUP BY O.id");
                ResultSet rst = pstm.executeQuery();
                while(rst.next()){
                    orders.add(new CustomEntity(rst.getString(1),rst.getString(2),
                            rst.getDate(3),rst.getString(4),rst.getBigDecimal(5)));
                }
                return orders;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
    }

}
