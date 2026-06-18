package com.jr.mini_bank_api.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequest(
        @NotBlank String name,
        @NotBlank @Email String email) {
}
