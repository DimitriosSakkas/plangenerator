package com.dimi.plangenerator.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LoanDataDto {

    private double loanAmount;
    private double nominalRate;
    private short duration;
    private LocalDate startDate;
}
