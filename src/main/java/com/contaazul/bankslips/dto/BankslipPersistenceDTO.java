package com.contaazul.bankslips.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BankslipPersistenceDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("due_date")
    private Date dueDate;

    @JsonProperty("total_in_cents")
    private Long priceInCents;

    @JsonProperty("customer")
    private String customer;

}
