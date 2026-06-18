package com.jr.mini_bank_api.customer.dto;

import java.time.Instant;

import com.jr.mini_bank_api.customer.CustomerStatus;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        CustomerStatus status,
        Instant createdAt,
        Instant statusChangedAt,
        String statusChangeReason) {
}
