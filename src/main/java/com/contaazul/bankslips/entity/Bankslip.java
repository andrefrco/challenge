package com.contaazul.bankslips.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bankslip {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private BankslipStatus status;

    private Date dueDate;

    private BigDecimal priceInCents;

    private String customer;

    private Date paymentDate;
}
