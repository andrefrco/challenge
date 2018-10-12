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
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankslipPersistenceDTO {

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("due_date")
    private Date dueDate;

    @NotNull
    @JsonProperty("total_in_cents")
    private Long priceInCents;

    @NotNull
    @JsonProperty("customer")
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
