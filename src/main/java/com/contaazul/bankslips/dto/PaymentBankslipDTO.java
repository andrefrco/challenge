package com.contaazul.bankslips.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentBankslipDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("payment_date")
    private Date paymentDate;

}
