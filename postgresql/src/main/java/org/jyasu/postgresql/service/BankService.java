package org.jyasu.postgresql.service;

import java.math.BigDecimal;

import org.jyasu.postgresql.account.entity.Account;
import org.jyasu.postgresql.account.repository.AccountRepository;
import org.jyasu.postgresql.transaction.entity.Transaction;
import org.jyasu.postgresql.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

    private final AccountRepository accountRepositoryDb1;
    private final TransactionRepository transactionRepositoryDb2;

    public BankService(AccountRepository accountRepositoryDb1, TransactionRepository transactionRepositoryDb2) {
        this.accountRepositoryDb1 = accountRepositoryDb1;
        this.transactionRepositoryDb2 = transactionRepositoryDb2;
    }

    @Transactional
    public void transferMoney(Long accountId, BigDecimal amount) {
        // Step 1: Update account balance in db1
        Account account = accountRepositoryDb1.findById(accountId).orElseThrow();
        account.setBalance(account.getBalance().subtract(amount));
        accountRepositoryDb1.save(account);

        // Step 2: Insert transaction record in db2
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transactionRepositoryDb2.save(transaction);
        
        // Both DB operations will be committed together, or rolled back together
    }

    public Account createAccount(long accountId, BigDecimal amount) {
        Account account = new Account();
        account.setBalance(amount);
        return accountRepositoryDb1.save(account);
    }
}