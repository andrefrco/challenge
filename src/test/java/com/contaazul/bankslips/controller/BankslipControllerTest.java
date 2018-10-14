package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankslipControllerTest {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private MockMvc mockMvc;

    private Date formatterDate(String dueDate) throws ParseException {
        return formatter.parse( dueDate );
    }

    private String formatterJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString( object );
    }

    @Test
    public void persistWhenCreatedTest() throws Exception {
        BankslipPersistenceDTO bankslipPersistenceDTO = BankslipPersistenceDTO.builder()
                .dueDate( formatterDate( "2018-01-01" ) )
                .priceInCents( BigDecimal.valueOf( 100000 ) )
                .customer( "Trillian Company" )
                .build();

        mockMvc.perform( post( "/rest/bankslips" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( formatterJson( bankslipPersistenceDTO ) )
                .accept( MediaType.APPLICATION_JSON ))
                .andExpect( status().isCreated() );
    }

    @Test
    public void persistWhenBadRequestTest() throws Exception {
        BankslipPersistenceDTO bankslipPersistenceDTO = null;

        mockMvc.perform( post( "/rest/bankslips" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( formatterJson( bankslipPersistenceDTO ) ))
                .andExpect( status().isBadRequest() );

    }

    @Test
    public void persistWhenUnprocessableEntityTest() throws Exception {
        BankslipPersistenceDTO bankslipPersistenceDTO = new BankslipPersistenceDTO();

        mockMvc.perform( post( "/rest/bankslips" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( formatterJson( bankslipPersistenceDTO ) ))
                .andExpect( status().isUnprocessableEntity() );
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform( get( "/rest/bankslips")
                .contentType( MediaType.APPLICATION_JSON ))
                .andExpect( status().isOk() );
    }

    public void findByIdWhenOk() {

    }

    public void findByIdWhenNotFound() {

    }

    public void payWhenOk() {

    }

    public void payWhenWhenNotFound() {

    }

    public void cancelWhenOk() {

    }

    public void cancelWhenNotFound() {

    }

}
