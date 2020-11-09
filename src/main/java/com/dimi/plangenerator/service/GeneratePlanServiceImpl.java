package com.dimi.plangenerator.service;

import com.dimi.plangenerator.model.dto.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.dto.LoanDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GeneratePlanServiceImpl implements GeneratePlanService {

    private static final byte daysInMonth = 30;
    private static final short daysInYear = 360;

    @Override
    public List<BorrowerPaymentsDTO> generatePlan(LoanDataDto loanDataDto) {
        double annuity = calculateAnnuity(loanDataDto);
        float interest = calculateInterest(loanDataDto, daysInMonth, daysInYear);
        double calculatePrincipal = calculatePrincipal(annuity, interest);
        System.out.println(annuity);
        System.out.println(interest);
        System.out.println(calculatePrincipal);
        return null;
    }

    private float calculateInterest(LoanDataDto loanDataDto, byte daysInMonth, short daysInYear) {
        return (loanDataDto.getNominalRate() * daysInMonth * loanDataDto.getLoanAmount()) / daysInYear;
    }

    private double calculatePrincipal(double annuity, float interest) {
        return annuity - interest;
    }

    private double calculateAnnuity(LoanDataDto loanDataDto) {
        float monthlyRate = loanDataDto.getNominalRate() / 12;
        return ((loanDataDto.getLoanAmount() * monthlyRate)
                / (1 - Math.pow(1 + monthlyRate, -loanDataDto.getDuration())));
    }
}
