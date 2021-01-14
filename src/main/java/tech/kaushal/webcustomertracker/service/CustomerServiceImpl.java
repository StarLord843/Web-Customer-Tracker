package tech.kaushal.webcustomertracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.kaushal.webcustomertracker.model.Customer;
import tech.kaushal.webcustomertracker.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        repository.save(customer);
        return repository.findByFirstNameAndLastNameAndEmail(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }

    @Override
    public Customer getCustomer(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteCustomer(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        if(theSearchName.isEmpty())
            return repository.findAll();
        return repository.findAllByFirstNameOrLastName(theSearchName, theSearchName);
    }
}
