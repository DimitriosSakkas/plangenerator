package com.dimi.plangenerator.service;

import com.dimi.plangenerator.model.dto.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.dto.LoanDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GeneratePlanServiceImpl implements GeneratePlanService {

    @Override
    public List<BorrowerPaymentsDTO> generatePlan(LoanDataDto loanDataDto) {
        double result = calculateAnnuity(loanDataDto);
        System.out.println(result);
        return null;
    }

    private float calculateInterest(float interestRate, float initialOutstandingPrincipal,
                                    byte daysInMonth, short daysInYear) {
        return (interestRate * daysInMonth * initialOutstandingPrincipal) / daysInYear;
    }

    private float calculatePrincipal(float annuity, float interest) {
        return annuity - interest;
    }

    private double calculateAnnuity(LoanDataDto loanDataDto) {
        float monthlyRate = loanDataDto.getNominalRate() / 12;
        return ((loanDataDto.getLoanAmount() * monthlyRate)
                / (1 - Math.pow(1 + monthlyRate, -loanDataDto.getDuration())));
    }
}
