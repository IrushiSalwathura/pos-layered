package dao.impl;

import dao.CustomerDAO;
import entity.Customer;

import java.util.List;

public class CustomerDAOTest {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        List<Object> allCustomer = customerDAO.findAll();
        for (Object c : allCustomer) {
            Customer customer = (Customer) c;
            System.out.println(customer);

        }
        assert false : "Pissu double";
    }
}
