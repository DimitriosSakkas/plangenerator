package com.dimi.plangenerator.service;


import com.dimi.plangenerator.mockdata.MockData;
import com.dimi.plangenerator.model.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.LoanDataDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GeneratePlanServiceImplTest {

    @InjectMocks
    private GeneratePlanServiceImpl generatePlanService;

    private LoanDataDto loanDataDto;
    private List<BorrowerPaymentsDTO> borrowerPaymentsDTO1;

    @BeforeEach
    public void init() {
        loanDataDto = MockData.createLoanDataDto();
        borrowerPaymentsDTO1 = MockData.createBorrowerPaymentsDTO1();
        borrowerPaymentsDTO2 = MockData.createBorrowerPaymentsDTO2();
    }

    @Test
    public void shouldReturnRepaymentPlan() {
        // given


        // when
        List<BorrowerPaymentsDTO> result = generatePlanService
                .generatePlan(loanDataDto);


        // then
        Assertions.assertAll(
                "check if repayment plan has the right values",
                () -> assertEquals(borrowerPaymentsDTO1, result.get(0)),
                () -> assertEquals(borrowerPaymentsDTO2, result.get(1))
        );

    }

}
