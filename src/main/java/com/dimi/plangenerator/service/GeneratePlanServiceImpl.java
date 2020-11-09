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
import java.util.stream.IntStream;

@Service
@Slf4j
public class GeneratePlanServiceImpl implements GeneratePlanService {

    private static final byte daysInMonth = 30;
    private static final short daysInYear = 360;

    @Override
    public List<BorrowerPaymentsDTO> generatePlan(LoanDataDto loanDataDto) {
        double annuity = calculateAnnuity(loanDataDto);

        LinkedList<BorrowerPaymentsDTO> dtoList = new LinkedList<>();
        LocalDate startDate = loanDataDto.getStartDate();
        dtoList
                .add(repaymentPlan(annuity, loanDataDto.getNominalRate(),
                        loanDataDto.getLoanAmount(), startDate));
        IntStream
                .iterate(1, i -> i + 1)
                .limit(loanDataDto.getDuration() - 1)
                .forEach(i ->
                        dtoList.add(repaymentPlan(annuity,
                                loanDataDto.getNominalRate(),
                                dtoList.getLast().getRemainingOutstandingPrincipal(),
                                startDate.plusMonths(i))));
        return dtoList;
    }

    private BorrowerPaymentsDTO repaymentPlan(double annuity, double rate,
                                              double initialOutstandingPrincipal, LocalDate date) {
        double interest = calculateInterest(rate, initialOutstandingPrincipal);
        double principal = calculatePrincipal(annuity, interest, initialOutstandingPrincipal);
        double borrowerPaymentAmount = calculateBorrowerPaymentAmount(principal, interest);
        double remainingOutstandingPrincipal =
                calculateRemainingOutstandingPrincipal(initialOutstandingPrincipal, principal);

        return BorrowerPaymentsDTO
                .builder()
                .date(date)
                .interest(interest)
                .principal(principal)
                .borrowerPaymentAmount(borrowerPaymentAmount)
                .initialOutstandingPrincipal(initialOutstandingPrincipal)
                .remainingOutstandingPrincipal(remainingOutstandingPrincipal)
                .build();
    }

    private double calculateAnnuity(LoanDataDto loanDataDto) {
        double monthlyRate = loanDataDto.getNominalRate() * 0.01 / 12;
        return roundValues(loanDataDto.getLoanAmount() * monthlyRate
                / (1 - Math.pow(1 + monthlyRate, -loanDataDto.getDuration())));
    }

    private double calculateInterest(double interestRate, double initialOutstandingPrincipal) {
        return roundValues(interestRate * 0.01 * daysInMonth * initialOutstandingPrincipal
                / daysInYear);
    }

    private double calculatePrincipal(double annuity, double interest, double initialOutstandingPrincipal) {
        return Math.min(roundValues(annuity - interest), initialOutstandingPrincipal);
    }

    private double calculateBorrowerPaymentAmount(double principal, double interest) {
        return roundValues(principal + interest);
    }

    private double calculateRemainingOutstandingPrincipal(double initialOutstandingPrincipal, double principal) {
        return roundValues(initialOutstandingPrincipal - principal);
    }

    private double roundValues(double input) {
        return new BigDecimal(input).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

}
