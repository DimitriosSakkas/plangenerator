package com.dimi.plangenerator.service;

import com.dimi.plangenerator.model.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.LoanDataDto;

import java.util.List;

public interface GeneratePlanService {

  List<BorrowerPaymentsDTO> generatePlan(LoanDataDto loanDataDto);
}
