package com.bank.alfalah.feature.CompanyOnboarding.controller;

import com.bank.alfalah.feature.CompanyOnboarding.entity.CompanyOnboarding;
import com.bank.alfalah.feature.CompanyOnboarding.facade.CompanyOnboardingFacade;
import com.bank.alfalah.feature.CompanyOnboarding.service.CompanyOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyOnboardingController {

    @Autowired
    private CompanyOnboardingFacade companyOnboardingServiceFacade;

    @PostMapping("/onboard")
    public CompanyOnboarding onboardCompany(@RequestBody CompanyOnboarding companyOnboarding) {
        return companyOnboardingServiceFacade.saveCompany(companyOnboarding);
    }
}
