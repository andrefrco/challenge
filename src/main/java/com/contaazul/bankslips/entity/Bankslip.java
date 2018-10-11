package com.contaazul.bankslips.entity;

import com.contaazul.bankslips.validations.UUID;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bankslip")
public class Bankslip {

    @Id
    @UUID
    private String id;

    @Enumerated(EnumType.STRING)
    private BankslipStatus status;

    private Date dueDate;

    private Long priceInCents;

    private String customer;
}
