package com.dimi.plangenerator.service;

import com.dimi.plangenerator.model.dto.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.dto.LoanDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GeneratePlanServiceImpl implements GeneratePlanService {

    private static final byte daysInMonth = 30;
    private static final short daysInYear = 360;

    @Override
    public List<BorrowerPaymentsDTO> generatePlan(LoanDataDto loanDataDto) {
        List<BorrowerPaymentsDTO> dtoList = new ArrayList<>();

        double monthlyRate = loanDataDto.getNominalRate() * 0.01 / 12;
        double annuity = calculateAnnuity(monthlyRate, loanDataDto.getLoanAmount(), loanDataDto.getDuration());
        System.out.println(annuity);

        BorrowerPaymentsDTO dto1 = new BorrowerPaymentsDTO();
        double interest1 = calculateInterest(loanDataDto.getNominalRate(), loanDataDto.getLoanAmount(), daysInMonth, daysInYear);
        dto1.setInterest(interest1);
        dto1.setPrincipal(calculatePrincipal(annuity, interest1));
        //dto1.setRemainingOutstandingPrincipal(c);
        //dto1.set();
       // dto1

        for (int month = 1; month <= loanDataDto.getDuration(); month++) {
            BorrowerPaymentsDTO dto = new BorrowerPaymentsDTO();

            double interest = calculateInterest(loanDataDto.getNominalRate(), dto.getRemainingOutstandingPrincipal(), daysInMonth, daysInYear);
            System.out.println(interest);
            dto.setInterest(interest);
            double calculatePrincipal = calculatePrincipal(annuity, interest);
            // System.out.println(calculatePrincipal);
            dtoList.add(dto);
        }

        return null;
    }

    private double calculateAnnuity(double monthlyRate, double loanAmount, short duration) {
        return roundValues(loanAmount * monthlyRate / (1 - Math.pow(1 + monthlyRate, -duration)));
    }

    private double calculateInterest(double interestRate, double outstandingPrincipal, byte daysInMonth, short daysInYear) {
        return roundValues(interestRate * 0.01 * daysInMonth * outstandingPrincipal / daysInYear);
    }

    private double calculatePrincipal(double annuity, double interest) {
        return roundValues(annuity - interest);
    }

    private double calculateBorrowerPaymentAmount(double principal, double interest) {
        return principal + interest;
    }

    private double calculateRemainingOutstandingPrincipal(double previousOutstandingPrincipal, double principal) {
        return previousOutstandingPrincipal - principal;
    }

    private byte calculateDaysInMonth(LocalDate date) {
        return (byte) (30 - date.getDayOfMonth());
    }

    private byte calculateMothnsInYear(LocalDate date) {
        return (byte) (12 - date.getMonthValue());
    }

    private double roundValues(double input) {
        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

}
