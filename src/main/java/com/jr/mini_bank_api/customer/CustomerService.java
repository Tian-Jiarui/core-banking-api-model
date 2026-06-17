package com.jr.mini_bank_api.customer;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jr.mini_bank_api.customer.dto.CreateCustomerRequest;
import com.jr.mini_bank_api.customer.dto.CustomerResponse;
import com.jr.mini_bank_api.customer.dto.UpdateCustomerRequest;
import com.jr.mini_bank_api.exception.ResourceNotFoundException;

@Service
public class CustomerService {
    private final CustomerRepository repo;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repo, CustomerMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public CustomerResponse create(CreateCustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        return mapper.toResponse(repo.save(customer));
    }

    public CustomerResponse get(Long id) {
        return mapper.toResponse(getEntity(id));
    }

    public CustomerResponse update(Long id, UpdateCustomerRequest request) {
        Customer existing = getEntity(id);
        mapper.applyUpdate(existing, request);
        return mapper.toResponse(repo.save(existing));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Page<CustomerResponse> list(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional
    public CustomerResponse deactivate(Long id, String reason) {
        // Step 1: Load the existing customer (throws if not found)
        Customer customer = getEntity(id);

        // Step 2: Modify fields
        customer.setStatus(CustomerStatus.INACTIVE);
        customer.setStatusChangedAt(Instant.now());
        customer.setStatusChangeReason(reason);

        // Step 3: Save the updated record
        return mapper.toResponse(repo.save(customer));
    }

    @Transactional
    public CustomerResponse activate(Long id) {
        Customer customer = getEntity(id);

        customer.setStatus(CustomerStatus.ACTIVE);
        customer.setStatusChangedAt(Instant.now());
        customer.setStatusChangeReason(null);

        return mapper.toResponse(repo.save(customer));
    }

    private Customer getEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }
}
