package com.contaazul.bankslips.dto;

import com.contaazul.bankslips.entity.Bankslip;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankslipDTO {

    private final String id;

    private final String customer;

    private final String status;

    private BigDecimal fine;

    @JsonProperty("total_in_cents")
    private final BigDecimal priceInCents;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("due_date")
    private final Date dueDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("payment_date")
    private Date paymentDate;

    public BankslipDTO(Bankslip bankslip) {
        this.id = bankslip.getId();
        this.dueDate = bankslip.getDueDate();
        this.priceInCents = bankslip.getPriceInCents();
        this.customer = bankslip.getCustomer();
        this.status = String.valueOf(bankslip.getStatus());
        this.fine = bankslip.getFine();
        this.paymentDate = bankslip.getPaymentDate();
    }
}
