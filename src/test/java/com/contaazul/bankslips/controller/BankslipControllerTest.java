package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.BankslipsApplication;
import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BankslipsApplication.class)
public class BankslipControllerTest {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private MockMvc mockMvc;

    private Date formatterDate(String dueDate) throws ParseException {
        return formatter.parse(dueDate);
    }

    private String formatterJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    public void persistWhenCreated() throws Exception {

        BankslipPersistenceDTO bankslipPersistenceDTO = BankslipPersistenceDTO.builder()
                .dueDate( formatterDate( "2018-01-01" ) )
                .priceInCents( BigDecimal.valueOf(100000) )
                .customer( "Trillian Company" )
                .build();

        mockMvc.perform( post( "/bankslips" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( formatterJson( bankslipPersistenceDTO ) )
                .accept( MediaType.APPLICATION_JSON ))
                .andExpect(status().isCreated());
    }

    public void persistWhenBadRequest() throws Exception {

        BankslipPersistenceDTO bankslipPersistenceDTO = null;

        mockMvc.perform( post( "/bankslips" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( formatterJson( bankslipPersistenceDTO ) )
                .accept( MediaType.APPLICATION_JSON ))
                .andExpect( status().isBadRequest() );

    }

    public void persistWhenUnprocessableEntity() throws Exception {

        BankslipPersistenceDTO bankslipPersistenceDTO = new BankslipPersistenceDTO();

        mockMvc.perform( post( "/bankslips" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( formatterJson( bankslipPersistenceDTO ) )
                .accept( MediaType.APPLICATION_JSON ))
                .andExpect( status().isUnprocessableEntity() );
    }

}
