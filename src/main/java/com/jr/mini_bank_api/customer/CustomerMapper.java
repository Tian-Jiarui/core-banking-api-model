package com.jr.mini_bank_api.customer;

import org.springframework.stereotype.Component;

import com.jr.mini_bank_api.customer.dto.CreateCustomerRequest;
import com.jr.mini_bank_api.customer.dto.CustomerResponse;
import com.jr.mini_bank_api.customer.dto.UpdateCustomerRequest;

@Component
public class CustomerMapper {

    public Customer toEntity(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        return customer;
    }

    public void applyUpdate(Customer entity, UpdateCustomerRequest request) {
        entity.setName(request.name());
        entity.setEmail(request.email());
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getStatusChangedAt(),
                customer.getStatusChangeReason());
    }
}
