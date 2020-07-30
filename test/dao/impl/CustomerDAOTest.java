package dao.impl;

import dao.CustomerDAO;
import entity.Customer;

import java.util.List;

public class CustomerDAOTest {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        List<Customer> allCustomer = customerDAO.findAllCustomers();
        for (Customer customer : allCustomer) {
            System.out.println(customer);

        }
        assert false : "Pissu double";
    }
}
