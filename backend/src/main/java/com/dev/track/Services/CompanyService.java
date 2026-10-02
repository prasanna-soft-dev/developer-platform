package com.dev.track.Services;

import com.dev.track.Entity.Company;

import java.util.List;

public interface CompanyService {
    Company create(String companyName);
    Company update(Long id,String companyName);
    void delete(Long id);
    List<Company> findAll();
}
