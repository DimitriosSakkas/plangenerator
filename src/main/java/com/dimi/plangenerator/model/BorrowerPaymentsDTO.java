package com.dimi.plangenerator.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BorrowerPaymentsDTO {

    private double borrowerPaymentAmount;
    private LocalDate date;
    private double initialOutstandingPrincipal;
    private double interest;
    private double principal;
    private double remainingOutstandingPrincipal;
}
