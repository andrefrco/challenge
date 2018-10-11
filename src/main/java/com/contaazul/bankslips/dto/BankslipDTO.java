package com.contaazul.bankslips.dto;

import com.contaazul.bankslips.entity.Bankslip;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class BankslipDTO {

    @JsonProperty("id")
    private final String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("due_date")
    private final Date dueDate;

    @JsonProperty("total_in_cents")
    private final Long priceInCents;

    @JsonProperty("customer")
    private final String customer;

    @JsonProperty("status")
    private final String status;

    public BankslipDTO(Bankslip bankslip) {
        this.id = bankslip.getId();
        this.dueDate = bankslip.getDueDate();
        this.priceInCents = bankslip.getPriceInCents();
        this.customer = bankslip.getCustomer();
        this.status = String.valueOf(bankslip.getStatus());
    }
}
