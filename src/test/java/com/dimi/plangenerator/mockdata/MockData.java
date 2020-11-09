package com.dimi.plangenerator.mockdata;

import com.dimi.plangenerator.model.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.LoanDataDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockData {

    private static final short duration = 2;
    private static final double loanAmount = 2000;
    private static final double nominalRate = 1.0;
    private static final String startDate = "2018-01-01";
    private static final String endDate = "2018-02-01";

    private static final double remainingOutstandingPrincipal1 = 1000.42;
    private static final double borrowerPaymentAmount1 = 1001.25;
    private static final double principal1 = 999.58;
    private static final double interest1 = 2000;

    private static final double remainingOutstandingPrincipal2 = 0;
    private static final double borrowerPaymentAmount2 = 1000.42;
    private static final double principal2 = 999.58;
    private static final double interest2 = 1000.42;

    public static LoanDataDto createLoanDataDto() {
        LoanDataDto dto = new LoanDataDto();
        dto.setDuration(duration);
        dto.setLoanAmount(loanAmount);
        dto.setNominalRate(nominalRate);
        dto.setStartDate(LocalDate.parse(startDate));
        return dto;
    }

    public static List<BorrowerPaymentsDTO> createBorrowerPaymentsDTOs() {
        List<BorrowerPaymentsDTO> dtos = new ArrayList<>();
        dtos.add(createBorrowerPaymentsDTO1());
        dtos.add(createBorrowerPaymentsDTO2());
        return dtos;
    }

    private static BorrowerPaymentsDTO createBorrowerPaymentsDTO1() {
        return BorrowerPaymentsDTO
                .builder()
                .date(LocalDate.parse(startDate))
                .initialOutstandingPrincipal(loanAmount)
                .remainingOutstandingPrincipal(remainingOutstandingPrincipal1)
                .borrowerPaymentAmount(borrowerPaymentAmount1)
                .principal(principal1)
                .interest(interest1)
                .build();
    }

    private static BorrowerPaymentsDTO createBorrowerPaymentsDTO2() {
        return BorrowerPaymentsDTO
                .builder()
                .date(LocalDate.parse(endDate))
                .initialOutstandingPrincipal(remainingOutstandingPrincipal1)
                .remainingOutstandingPrincipal(remainingOutstandingPrincipal2)
                .borrowerPaymentAmount(borrowerPaymentAmount2)
                .principal(principal2)
                .interest(interest2)
                .build();
    }
}
