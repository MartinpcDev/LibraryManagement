package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.dto.request.SaveCustomer;
import com.martin.projects.Library.dto.response.CustomerDto;
import com.martin.projects.Library.exception.DuplicatedNameException;
import com.martin.projects.Library.exception.NotFoundElementException;
import com.martin.projects.Library.mapper.CustomerMapper;
import com.martin.projects.Library.persistence.entity.Customer;
import com.martin.projects.Library.persistence.repository.CustomerRepository;
import com.martin.projects.Library.service.CustomerService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<CustomerDto> findAllCustomers() {
    return CustomerMapper.toCustomerDtoList(customerRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<CustomerDto> findAllCustomersByName(String name) {
    return CustomerMapper.toCustomerDtoList(
        customerRepository.findAllByFullNameContainingIgnoreCase(name));
  }

  @Transactional(readOnly = true)
  @Override
  public CustomerDto findCustomerById(Long id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El id no pertenece a ningun Cliente"));
    return CustomerMapper.toCustomerDto(customer);
  }

  @Override
  public CustomerDto createCustomer(SaveCustomer customerDto) {
    boolean emailExists = customerRepository.existsByEmail(customerDto.getEmail());
    boolean phoneExists = customerRepository.existsByPhone(customerDto.getPhone());

    if (emailExists) {
      throw new DuplicatedNameException("el email ya esta en uso");
    } else if (phoneExists) {
      throw new DuplicatedNameException("el phone ya esta en uso");
    }

    Customer customer = CustomerMapper.toCustomerEntity(customerDto);
    Customer customerCreated = customerRepository.save(customer);

    return CustomerMapper.toCustomerDto(customerCreated);
  }

  @Override
  public CustomerDto updateCustomer(Long id, SaveCustomer customerDto) {
    Customer customerExists = customerRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El cliente requerido no existe"));

    boolean emailExists = customerRepository.existsByEmail(customerDto.getEmail());
    boolean phoneExists = customerRepository.existsByPhone(customerDto.getPhone());

    if (emailExists && !Objects.equals(customerExists.getEmail(), customerDto.getEmail())) {
      throw new DuplicatedNameException("el email ya esta en uso");
    } else if (phoneExists && !Objects.equals(customerExists.getPhone(), customerDto.getPhone())) {
      throw new DuplicatedNameException("el phone ya esta en uso");
    }

    CustomerMapper.updateEntityCustomer(customerExists, customerDto);
    Customer customerUpdated = customerRepository.save(customerExists);
    return CustomerMapper.toCustomerDto(customerUpdated);
  }

  @Override
  public void deleteCustomer(Long id) {
    Customer customerExists = customerRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El cliente requerido no existe"));

    customerRepository.delete(customerExists);
  }
}
