package com.dimi.plangenerator.model.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanDataDto {

    private float loanAmount;
    private float nominalRate;
    private short duration;
    private LocalDate startDate;
}
