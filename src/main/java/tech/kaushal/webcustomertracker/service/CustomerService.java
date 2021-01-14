package tech.kaushal.webcustomertracker.service;

import tech.kaushal.webcustomertracker.model.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomers(String theSearchName);
}
