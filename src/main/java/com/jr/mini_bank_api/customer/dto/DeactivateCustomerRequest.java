package com.jr.mini_bank_api.customer.dto;

import jakarta.validation.constraints.NotBlank;

public record DeactivateCustomerRequest(
        @NotBlank String reason) {
}
