package com.contaazul.bankslips.dto;

import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankslipPersistenceDTO {

    @NotNull(message = "Due date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("due_date")
    private Date dueDate;

    @NotNull(message = "Price is required")
    @JsonProperty("total_in_cents")
    private BigDecimal priceInCents;

    @NotNull(message = "Customer is required")
    private String customer;

    public Bankslip toBankSlip(String uuid, BankslipStatus status) {
        return Bankslip.builder()
                .id( uuid )
                .dueDate( dueDate )
                .priceInCents( priceInCents )
                .customer( customer )
                .status( status )
                .build();
    }

}
