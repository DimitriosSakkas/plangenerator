package com.dimi.plangenerator.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowerPaymentsDTO {

    private double borrowerPaymentAmount;
    private LocalDate date;
    private double interest;
    private double principal;
    private double remainingOutstandingPrincipal;
}
