package com.contaazul.bankslips.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class PaymentDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("payment_date")
    private final Date paymentDate;

}
