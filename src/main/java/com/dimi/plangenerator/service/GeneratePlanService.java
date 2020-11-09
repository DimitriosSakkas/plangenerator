package com.dimi.plangenerator.service;

import com.dimi.plangenerator.model.dto.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.dto.LoanDataDto;

import java.util.List;

public interface GeneratePlanService {

    List<BorrowerPaymentsDTO> generatePlan(LoanDataDto loanDataDto);
}
