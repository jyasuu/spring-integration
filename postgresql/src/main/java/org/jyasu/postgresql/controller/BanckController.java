package org.jyasu.postgresql.controller;

import java.math.BigDecimal;

import org.jyasu.postgresql.account.entity.Account;
import org.jyasu.postgresql.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/bank")
public class BanckController {
    
    @Autowired
    private BankService bankService;

    @GetMapping("/create")
    public ResponseEntity<String> createAccount() {
        try {
            Account account = bankService.createAccount(0L, BigDecimal.valueOf(100));
            return ResponseEntity.ok("Account created successfully"+ account);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney() {
        try {
            bankService.transferMoney(1L, BigDecimal.valueOf(100));
            return ResponseEntity.ok("Transfer successfully");
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
