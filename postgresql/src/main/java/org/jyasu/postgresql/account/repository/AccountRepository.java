package org.jyasu.postgresql.account.repository;

import org.jyasu.postgresql.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}