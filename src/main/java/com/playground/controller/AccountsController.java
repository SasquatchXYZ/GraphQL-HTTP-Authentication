package com.playground.controller;

import com.playground.domain.BankAccount;
import com.playground.domain.Client;
import com.playground.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class AccountsController {
    @Autowired
    BankService bankService;

    @QueryMapping
    List<BankAccount> accounts() {
        log.info("Getting Accounts");
        return bankService.getAccounts();
    }

    @SchemaMapping(typeName = "BankAccount", field = "client")
    Client getClient(BankAccount account) {
        log.info("Getting Client for Account: {}", account.id());
        return bankService.getClientByAccountId(account.id());
    }
}
