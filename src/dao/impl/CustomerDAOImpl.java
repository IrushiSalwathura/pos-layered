package dao.impl;

import dao.CustomerDAO;
import db.DBConnection;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public List<Customer> findAll(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
            ArrayList<Customer> customers = new ArrayList<>();
            while(rst.next()){
                customers.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3)));
            }
            return customers;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    @Override
    public Customer find(String pk) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id = ?");
            pstm.setObject(1,pk);
            ResultSet rst = pstm.executeQuery();
            while(rst.next()){
                return new Customer(rst.getString(1),rst.getString(2),rst.getString(3));
            }
            return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Customer entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?)");
            pstm.setObject(1,entity.getId());
            pstm.setObject(2,entity.getName());
            pstm.setObject(3,entity.getAddress());
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Customer entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
            pstm.setObject(3,entity.getId());
            pstm.setObject(1,entity.getName());
            pstm.setObject(2,entity.getAddress());
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
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
            pstm.setObject(1,pk);
            return pstm.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public String getLastCustomerId(){

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Customer ORDER BY id DESC LIMIT 1");
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
