package dao.impl;

import dao.ItemDAO;
import db.DBConnection;
import entity.Customer;
import entity.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<Item> findAll() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item");
            ArrayList<Item> items = new ArrayList<>();
            while(rst.next()){
                items.add(new Item(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4)));
            }
            return items;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Item find(String pk) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code = ?");
            pstm.setObject(1,pk);
            ResultSet rst = pstm.executeQuery();
            while(rst.next()){
                return new Item(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4));
            }
            return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Item entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");
            pstm.setObject(1, entity.getCode());
            pstm.setObject(2, entity.getDescription());
            pstm.setObject(3, entity.getUnitPrice());
            pstm.setObject(4, entity.getQtyOnHand());
            return pstm.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Item entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
            pstm.setObject(4,entity.getCode());
            pstm.setObject(1,entity.getDescription());
            pstm.setObject(2,entity.getUnitPrice());
            pstm.setObject(3,entity.getQtyOnHand());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String pk) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
            pstm.setObject(1,pk);
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public String getLastItemCode(){

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item ORDER BY code DESC LIMIT 1");
            if(!rst.next()){
                return null;
            }else{
                return rst.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
