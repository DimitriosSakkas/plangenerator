package com.dimi.plangenerator.service;

import com.dimi.plangenerator.model.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.LoanDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class GeneratePlanServiceImpl implements GeneratePlanService {

    private static final byte daysInMonth = 30;
    private static final short daysInYear = 360;

    @Override
    public List<BorrowerPaymentsDTO> generatePlan(LoanDataDto loanDataDto) {
        LinkedList<BorrowerPaymentsDTO> dtoList = new LinkedList<>();

        double monthlyRate = loanDataDto.getNominalRate() * 0.01 / 12;
        double annuity = calculateAnnuity(monthlyRate, loanDataDto.getLoanAmount(), loanDataDto.getDuration());
        System.out.println(annuity);

        dtoList.add(repaymentPlan(annuity, loanDataDto.getNominalRate(), loanDataDto.getLoanAmount()));

        for (int month = 1; month <= loanDataDto.getDuration(); month++) {
            dtoList.add(repaymentPlan(annuity, loanDataDto.getNominalRate(), dtoList.getLast().getRemainingOutstandingPrincipal()));
        }

        return null;
    }

    private BorrowerPaymentsDTO repaymentPlan(double annuity, double rate, double initialOutstandingPrincipal) {
        BorrowerPaymentsDTO dto = new BorrowerPaymentsDTO();
        double interest = calculateInterest(rate, initialOutstandingPrincipal);
        double principal = calculatePrincipal(annuity, interest);
        double borrowerPaymentAmount = calculateBorrowerPaymentAmount(principal, interest);
        double remainingOutstandingPrincipal = calculateRemainingOutstandingPrincipal(initialOutstandingPrincipal, principal);

        dto.setDate(LocalDate.now());
        dto.setInterest(interest);
        dto.setPrincipal(principal);
        dto.setBorrowerPaymentAmount(borrowerPaymentAmount);
        dto.setRemainingOutstandingPrincipal(remainingOutstandingPrincipal);
        dto.setInitialOutstandingPrincipal(initialOutstandingPrincipal);

        return dto;
    }

    private double calculateAnnuity(double monthlyRate, double loanAmount, short duration) {
        return roundValues(loanAmount * monthlyRate / (1 - Math.pow(1 + monthlyRate, -duration)));
    }

    private double calculateInterest(double interestRate, double initialOutstandingPrincipal) {
        return roundValues(interestRate * 0.01 * daysInMonth * initialOutstandingPrincipal / daysInYear);
    }

    private double calculatePrincipal(double annuity, double interest) {
        return roundValues(annuity - interest);
    }

    private double calculateBorrowerPaymentAmount(double principal, double interest) {
        return roundValues(principal + interest);
    }

    private double calculateRemainingOutstandingPrincipal(double initialOutstandingPrincipal, double principal) {
        return roundValues(initialOutstandingPrincipal - principal);
    }

    private double roundValues(double input) {
        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

}
