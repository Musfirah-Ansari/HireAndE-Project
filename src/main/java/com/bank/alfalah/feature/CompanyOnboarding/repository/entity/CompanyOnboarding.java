package com.bank.alfalah.feature.CompanyOnboarding.repository.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Entity
@Table(name = "HireAtE_Company_Onboarding")
public class CompanyOnboarding implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    Long companyId;                  // Unique identifier for each company

    @Column(name = "company_name")
    String companyName;              // Name of the company

    @Column(name = "industry")
    String industry;                 // Industry the company belongs to

    @Column(name = "company_description")
    String companyDescription;       // A short description of the company

    @Column(name = "company_website")
    String companyWebsite;           // URL of the company's website

    @Column(name = "company_logo")
    String companyLogo;              // Logo image (URL or file path)

    @Column(name = "company_size")
    String companySize;              // Size of the company (Small, Medium, Large)

    @Column(name = "company_type")
    String companyType;              // Type of company (Startup, Established, Non-profit)

    @Column(name = "contact_name")
    String contactName;              // Name of the primary contact person

    @Column(name = "contact_email")
    String contactEmail;             // Email address of the primary contact

    @Column(name = "contact_phone")
    String contactPhone;             // Phone number of the primary contact

    @Column(name = "company_address")
    String companyAddress;           // Full address of the company

    @Column(name = "city")
    String city;                     // City where the company is located

    @Column(name = "state")
    String state;                    // State where the company is located

    @Column(name = "country")
    String country;                  // Country where the company is located

    @Column(name = "zip_code")
    String zipCode;                  // ZIP code for the company's location

    @Column(name = "established_year")
    int establishedYear;             // Year when the company was established

    @Column(name = "tax_id")
    String taxId;                    // Tax identification number

    @Column(name = "linkedin_profile")
    String linkedinProfile;          // URL of the company's LinkedIn profile

    @Column(name = "facebook_profile")
    String facebookProfile;          // URL of the company's Facebook page

    @Column(name = "twitter_handle")
    String twitterHandle;            // Company's Twitter handle
}
