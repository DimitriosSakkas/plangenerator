package com.dimi.plangenerator.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
public class LoanDataDto {

  @PositiveOrZero(message = "loanAmount must be zero or positive")
  private double loanAmount;

  @PositiveOrZero(message = "nominalRate must be zero or positive")
  private double nominalRate;

  @PositiveOrZero(message = "duration must be zero or positive")
  private short duration;

  @NotNull(message = "startDate cannot be null")
  private LocalDate startDate;
}
