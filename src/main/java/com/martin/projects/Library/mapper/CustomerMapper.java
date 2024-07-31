package com.martin.projects.Library.mapper;

import com.martin.projects.Library.dto.request.SaveCustomer;
import com.martin.projects.Library.dto.response.CustomerDto;
import com.martin.projects.Library.persistence.entity.Customer;
import java.util.List;

public class CustomerMapper {

  public static CustomerDto toCustomerDto(Customer customer) {
    if (customer == null) {
      return null;
    }

    return new CustomerDto(
        customer.getId(),
        customer.getFullName(),
        customer.getPhone(),
        customer.getEmail(),
        customer.getCreatedAt()
    );
  }

  public static List<CustomerDto> toCustomerDtoList(List<Customer> customers) {
    if (customers == null) {
      return null;
    }

    return customers.stream()
        .map(CustomerMapper::toCustomerDto)
        .toList();
  }

  public static Customer toCustomerEntity(SaveCustomer customerDto) {
    if (customerDto == null) {
      return null;
    }

    Customer customer = new Customer();
    customer.setFullName(customerDto.getFullName());
    customer.setPhone(customerDto.getPhone());
    customer.setEmail(customerDto.getEmail());

    return customer;
  }

  public static void updateEntityCustomer(Customer oldCustomer, SaveCustomer customerDto) {
    if (oldCustomer == null || customerDto == null) {
      return;
    }

    oldCustomer.setFullName(customerDto.getFullName());
    oldCustomer.setPhone(customerDto.getPhone());
    oldCustomer.setEmail(customerDto.getEmail());
  }
}
