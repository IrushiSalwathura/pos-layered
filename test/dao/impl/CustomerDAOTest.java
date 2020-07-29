package dao.impl;

import entity.Customer;

import java.util.List;

public class CustomerDAOTest {
    public static void main(String[] args) {
        List<Customer> allCustomer = CustomerDAOImpl.findAllCustomers();
        for (Customer customer : allCustomer) {
            System.out.println(customer);

        }
        assert false : "Pissu double";
    }
}
