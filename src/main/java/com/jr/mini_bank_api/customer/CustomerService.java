package com.jr.mini_bank_api.customer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.jr.mini_bank_api.exception.ResourceNotFoundException;

@Service
public class CustomerService {
    private final CustomerRepository repo;
    
    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }
    
    public Customer create(Customer customer) {
        return repo.save(customer);
    }
    
    public Customer get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }

    @PostMapping
    public List<Customer> list() {
        return repo.findAll();
    }

    public Customer update(Long id, Customer updated) {
        Customer existing = get(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public Page<Customer> list(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
