package dao.custom.impl;

import dao.custom.CustomerDAO;
import entity.Customer;

import java.util.List;

public class CustomerDAOTest {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        List<Customer> allCustomer = customerDAO.findAll();
        for (Object c : allCustomer) {
            Customer customer = (Customer) c;
            System.out.println(customer);

        }
        assert false : "Pissu double";
    }
}
