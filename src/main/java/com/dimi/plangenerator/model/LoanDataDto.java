package com.dimi.plangenerator.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LoanDataDto {

    @NonNull
    private double loanAmount;
    @NonNull
    private double nominalRate;
    @NonNull
    private short duration;
    @NonNull
    private LocalDate startDate;
}
