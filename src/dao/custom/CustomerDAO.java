package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public String getLastCustomerId();
}
