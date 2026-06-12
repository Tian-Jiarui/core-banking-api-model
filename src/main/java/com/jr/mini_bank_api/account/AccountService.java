package com.jr.mini_bank_api.account;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account create(Account account) {
        return repo.save(account);
    }
}