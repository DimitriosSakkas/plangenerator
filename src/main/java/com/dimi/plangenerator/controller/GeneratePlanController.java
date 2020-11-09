package com.dimi.plangenerator.controller;

import com.dimi.plangenerator.model.dto.BorrowerPaymentsDTO;
import com.dimi.plangenerator.model.dto.LoanDataDto;
import com.dimi.plangenerator.service.GeneratePlanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("generate-plan")
public class GeneratePlanController {


    private final GeneratePlanService generatePlanService;

    public GeneratePlanController(GeneratePlanService generatePlanService) {
        this.generatePlanService = generatePlanService;
    }

    @PostMapping
    public List<BorrowerPaymentsDTO> generatePlan(@RequestBody LoanDataDto loanDataDto) {
        return generatePlanService.generatePlan(loanDataDto);
    }

}
