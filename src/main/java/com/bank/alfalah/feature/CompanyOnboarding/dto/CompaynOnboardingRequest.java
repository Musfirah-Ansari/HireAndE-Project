package com.bank.alfalah.feature.CompanyOnboarding.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CompaynOnboardingRequest {
    Long companyId;                  // Unique identifier for each company
    String companyName;              // Name of the company
    String industry;                 // Industry the company belongs to
    String companyDescription;       // A short description of the company
    String companyWebsite;           // URL of the company's website
    String companyLogo;              // Logo image (URL or file path)
    String companySize;              // Size of the company (Small, Medium, Large)
    String companyType;              // Type of company (Startup, Established, Non-profit)
    String contactName;              // Name of the primary contact person
    String contactEmail;             // Email address of the primary contact
    String contactPhone;             // Phone number of the primary contact
    String companyAddress;           // Full address of the company
    String city;                     // City where the company is located
    String state;                    // State where the company is located
    String country;                  // Country where the company is located
    String zipCode;                  // ZIP code for the company's location
    int establishedYear;             // Year when the company was established
    String taxId;                    // Tax identification number
    String linkedinProfile;          // URL of the company's LinkedIn profile
    String facebookProfile;          // URL of the company's Facebook page
    String twitterHandle;            // Company's Twitter handle
}
