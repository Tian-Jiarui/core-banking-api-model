package com.jr.mini_bank_api.customer;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Customer deactivate(Long id, String reason) {
        // Step 1: Load the existing customer (throws if not found)
        Customer customer = get(id);

        // Step 2: Modify fields
        customer.setStatus(CustomerStatus.INACTIVE);
        customer.setStatusChangedAt(Instant.now());
        customer.setStatusChangeReason(reason);

        // Step 3: Save the updated record
        return repo.save(customer);
    }
    
    @Transactional
    public Customer activate(Long id) {
        Customer customer = get(id);

        customer.setStatus(CustomerStatus.ACTIVE);
        customer.setStatusChangedAt(Instant.now());
        customer.setStatusChangeReason(null);

        return repo.save(customer);
    }
}
