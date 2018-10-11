package com.contaazul.bankslips.repository;

import com.contaazul.bankslips.entity.Bankslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankslipRepository extends JpaRepository<Bankslip, String> {
}
