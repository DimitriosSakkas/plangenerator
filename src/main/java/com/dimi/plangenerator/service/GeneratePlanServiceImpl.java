package com.dimi.plangenerator.service;

import com.dimi.plangenerator.model.dto.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.dto.LoanDataDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneratePlanServiceImpl implements GeneratePlanService {

    @Override
    public List<BorrowerPaymentsDTO> generatePlan(LoanDataDto loanDataDto) {
        return null;
    }
}
