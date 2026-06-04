package com.jr.mini_bank_api.customer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
   private final CustomerService service;
   
   public CustomerController(CustomerService service) {
       this.service = service;
   }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return service.create(customer);
    }
    
    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) {
       return service.get(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer customer) {
        return service.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @GetMapping
    public Page<Customer> list (Pageable pageable) {
       return service.list(pageable);
    }
}
