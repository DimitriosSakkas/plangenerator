package com.dimi.plangenerator.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowerPaymentsDTO {

    private float borrowerPaymentAmount;
    private LocalDate date;
    private float interest;
    private float principal;
    private float remainingOutstandingPrincipal;
}
