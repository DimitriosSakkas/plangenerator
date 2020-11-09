package com.dimi.plangenerator.model.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanDataDto {

    private double loanAmount;
    private double nominalRate;
    private short duration;
    private LocalDate startDate;
}
