package com.bank.alfalah.feature.CompanyOnboarding.services;

import com.bank.alfalah.feature.CompanyOnboarding.dto.CompanyOnboardingRequest;
import com.bank.alfalah.feature.CompanyOnboarding.repository.entity.CompanyOnboarding;

public interface CompanyOnboardingServices {
    
   CompanyOnboarding save(CompanyOnboardingRequest request);

}
