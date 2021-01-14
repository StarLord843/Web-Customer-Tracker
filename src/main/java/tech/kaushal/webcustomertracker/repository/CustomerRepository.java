package tech.kaushal.webcustomertracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.kaushal.webcustomertracker.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findCustomersByOrderByFirstName();

    List<Customer> findAllByFirstNameOrLastName(String firstName, String lastName);

    Customer findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);


}
