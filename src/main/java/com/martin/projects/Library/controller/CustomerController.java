package com.martin.projects.Library.controller;

import com.martin.projects.Library.dto.request.SaveCustomer;
import com.martin.projects.Library.dto.response.CustomerDto;
import com.martin.projects.Library.service.CustomerService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<CustomerDto>> findAll(@RequestParam(required = false) String name) {

    List<CustomerDto> customerDtoList;

    if (StringUtils.hasText(name)) {
      customerDtoList = customerService.findAllCustomersByName(name);
    } else {
      customerDtoList = customerService.findAllCustomers();
    }

    return ResponseEntity.ok(customerDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDto> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(customerService.findCustomerById(id));
  }

  @PostMapping
  public ResponseEntity<CustomerDto> create(@RequestBody @Valid SaveCustomer customerDto) {
    CustomerDto customerCreated = customerService.createCustomer(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(customerCreated);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDto> update(@PathVariable("id") Long id,
      @RequestBody @Valid SaveCustomer customerDto) {
    CustomerDto customerUpdated = customerService.updateCustomer(id, customerDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerUpdated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
    customerService.deleteCustomer(id);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Cliente Eliminado Correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
