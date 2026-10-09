package com.dev.track.Services.Implementation;

import com.dev.track.Entity.Company;
import com.dev.track.Exception.DuplicateResourceException;
import com.dev.track.Exception.ResourceNotFoundException;
import com.dev.track.Repository.CompanyRepository;
import com.dev.track.Services.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ImplCompany implements CompanyService {
    private final CompanyRepository companyRepository;

    ImplCompany(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public Company create(String companyName) {

        Optional<Company> company =
                companyRepository.findByCompanyName(companyName);

        if(company.isPresent()) {
            Company existingCompany = company.get();
             if(existingCompany.getCompanyName().toLowerCase().equals(companyName.toLowerCase())) {
                 throw new DuplicateResourceException(
                         "Company with name '" + companyName + "' already exists"
                 );
             }
        }

        Company newCompany = new Company();
        newCompany.setCompanyName(companyName);

        return companyRepository.save(newCompany);
    }

    @Override
    public Company update(Long id, String companyName) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found" + id));
        company.setCompanyName(companyName);
        return companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found" + id));

        companyRepository.delete(company);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
