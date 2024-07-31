package com.martin.projects.Library.service;

import com.martin.projects.Library.dto.request.SaveCustomer;
import com.martin.projects.Library.dto.response.CustomerDto;
import java.util.List;

public interface CustomerService {

  List<CustomerDto> findAllCustomers();

  List<CustomerDto> findAllCustomersByName(String name);

  CustomerDto findCustomerById(Long id);

  CustomerDto createCustomer(SaveCustomer customerDto);

  CustomerDto updateCustomer(Long id, SaveCustomer customerDto);

  void deleteCustomer(Long id);
}
