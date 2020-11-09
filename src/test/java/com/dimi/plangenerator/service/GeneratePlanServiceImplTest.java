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

  @InjectMocks private GeneratePlanServiceImpl generatePlanService;

  private LoanDataDto loanDataDto;
  private List<BorrowerPaymentsDTO> borrowerPaymentsDTOs;

  @BeforeEach
  public void init() {
    loanDataDto = MockData.createLoanDataDto();
    borrowerPaymentsDTOs = MockData.createBorrowerPaymentsDTOs();
  }

  @Test
  public void shouldReturnRepaymentPlan() {
    // when
    List<BorrowerPaymentsDTO> result = generatePlanService.generatePlan(loanDataDto);

    // then
    Assertions.assertAll(
        "check if repayment plan has the right values",
        () ->
            assertEquals(
                borrowerPaymentsDTOs.get(1).getRemainingOutstandingPrincipal(),
                result.get(1).getRemainingOutstandingPrincipal(),
                0.1d),
        () ->
            assertEquals(
                borrowerPaymentsDTOs.get(1).getBorrowerPaymentAmount(),
                result.get(1).getBorrowerPaymentAmount(),
                0.1d),
        () ->
            assertEquals(
                borrowerPaymentsDTOs.get(1).getInitialOutstandingPrincipal(),
                result.get(1).getInitialOutstandingPrincipal(),
                0.1d),
        () ->
            assertEquals(
                borrowerPaymentsDTOs.get(1).getInterest(), result.get(1).getInterest(), 0.1d),
        () ->
            assertEquals(
                borrowerPaymentsDTOs.get(1).getPrincipal(), result.get(1).getPrincipal(), 0.1d),
        () -> assertEquals(borrowerPaymentsDTOs.get(1).getDate(), result.get(1).getDate()));
  }
}
