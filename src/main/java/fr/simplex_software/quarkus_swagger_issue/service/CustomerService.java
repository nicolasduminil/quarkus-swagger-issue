package fr.simplex_software.quarkus_swagger_issue.service;

import fr.simplex_software.quarkus_swagger_issue.domain.*;
import fr.simplex_software.quarkus_swagger_issue.exceptions.*;
import fr.simplex_software.quarkus_swagger_issue.repository.*;
import jakarta.enterprise.context.*;
import jakarta.inject.*;
import jakarta.transaction.*;

import java.util.*;

@ApplicationScoped
public class CustomerService
{
  @Inject
  CustomerRepository customerRepository;

  public List<CustomerDTO> getCustomers()
  {
    return customerRepository.findAll().stream().map(CustomerMapper.INSTANCE::fromEntity).toList();
  }

  public CustomerDTO getCustomer(Long id)
  {
    return CustomerMapper.INSTANCE.fromEntity(customerRepository.findByIdOptional(id)
      .orElseThrow(() -> new CustomerNotFoundException("### CustomerServiceImpl.getCustomer(): Customer not found for ID: " + id)));
  }

  public CustomerDTO getCustomerByEmail(String email)
  {
    return CustomerMapper.INSTANCE.fromEntity(customerRepository.find("email", email).singleResultOptional()
      .orElseThrow(() -> new CustomerNotFoundException("### CustomerServiceImpl.getCustomerByEmail(): Customer not found for email: " + email)));
  }

  @Transactional
  public CustomerDTO createCustomer(CustomerDTO customerDTO)
  {
    Customer customer = CustomerMapper.INSTANCE.toEntity(customerDTO);
    System.out.println ("### CustomerServiceImpl.createCustomer(): customer id: " + customer.getId());
    customer.setId(null);
    System.out.println ("### CustomerServiceImpl.createCustomer(): customer id: " + customer.getId());
    customerRepository.persistAndFlush(customer);
    return CustomerMapper.INSTANCE.fromEntity(customer);
  }

  @Transactional
  public CustomerDTO updateCustomer(CustomerDTO customerDTO)
  {
    Optional<Customer> optionalCustomer = customerRepository.findByIdOptional(customerDTO.id());
    return optionalCustomer.map(existingCustomer ->
    {
      CustomerMapper.INSTANCE.updateEntityFromDTO(customerDTO, existingCustomer);
      customerRepository.persist(existingCustomer);
      return CustomerMapper.INSTANCE.fromEntity(existingCustomer);
    }).orElseThrow(() -> new CustomerNotFoundException("### CustomerServiceImpl.updateCustomer(): Customer not found for id: " + customerDTO.id()));

  }

  @Transactional
  public void deleteCustomer(Long id)
  {
    customerRepository.deleteById(id);
  }

  public Customer findCustomerById(Long id)
  {
    return customerRepository.findById(id);
  }
}
