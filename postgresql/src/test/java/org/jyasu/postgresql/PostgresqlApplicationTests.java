package org.jyasu.postgresql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jyasu.postgresql.account.entity.Account;
import org.jyasu.postgresql.account.repository.AccountRepository;
import org.jyasu.postgresql.service.BankService;
import org.jyasu.postgresql.transaction.repository.TransactionRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostgresqlApplicationTests {

    @InjectMocks
	private BankService bankService;

	
    @Mock
    private AccountRepository accountRepository;
	
    @Mock
    private TransactionRepository transactionRepository;


	@Test
	void contextLoads() {
	}

	@Test
	void success() {
		Account mockAccount = new Account();
		mockAccount.setId(1l);
		mockAccount.setVersion(1);
		mockAccount.setBalance(BigDecimal.valueOf(1000l));;
		Mockito.when(accountRepository.save(any())).thenReturn(mockAccount);
		Account account = bankService.createAccount(0, null);

		assertEquals(1l, account.getId());
		assertEquals(1, account.getVersion());
		assertEquals(BigDecimal.valueOf(1000l), account.getBalance());
		
	}
	@Test
	void fail() {
		Account mockAccount = new Account();
		mockAccount.setId(1l);
		mockAccount.setVersion(1);
		mockAccount.setBalance(BigDecimal.valueOf(1000l));;
		Mockito.when(accountRepository.save(any())).thenReturn(mockAccount);
		Account account = bankService.createAccount(0, null);

		assertEquals(1l, account.getId());
		assertEquals(1, account.getVersion());
		assertEquals(BigDecimal.valueOf(1010l), account.getBalance());
		
	}

	

}
