package com.dimi.plangenerator.controller;

import com.dimi.plangenerator.mockdata.MockData;
import com.dimi.plangenerator.model.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.LoanDataDto;
import com.dimi.plangenerator.service.GeneratePlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GeneratePlanController.class)
public class GeneratePlanControllerTest {

    private static final String baseUrl = "/generate-plan";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GeneratePlanService generatePlanService;

    private LoanDataDto loanDataDto;
    private List<BorrowerPaymentsDTO> borrowerPaymentsDTOS;

    @BeforeEach
    public void init() {
        loanDataDto = MockData.createLoanDataDto();
        borrowerPaymentsDTOS = MockData.createBorrowerPaymentsDTOs();
    }

    @Test
    public void shouldGetExchangeRatesAndSaveItInDb() throws Exception {
        // given
        Mockito
                .when(generatePlanService.generatePlan(ArgumentMatchers.any(LoanDataDto.class)))
                .thenReturn(borrowerPaymentsDTOS);

        // when & then
        mockMvc
                .perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanDataDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }
}
