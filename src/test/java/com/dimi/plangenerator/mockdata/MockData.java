package com.dimi.plangenerator.mockdata;

import com.dimi.plangenerator.model.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.LoanDataDto;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static LoanDataDto createLoanDataDto() {
        return new LoanDataDto();
    }

    public static List<BorrowerPaymentsDTO> createBorrowerPaymentsDTOs() {
        return new ArrayList<>();
    }
}
