package com.dev.track.Controller;

import com.dev.track.DTO.ApiResponse;
import com.dev.track.Entity.Company;
import com.dev.track.Services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Company>> createCompany(
            @RequestParam String companyName) {

        Company company = companyService.create(companyName);

        ApiResponse<Company> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Company created successfully");
        response.setData(company);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Company>>> getCompanies() {

        List<Company> companies = companyService.findAll();

        ApiResponse<List<Company>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Companies fetched successfully");
        response.setData(companies);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> updateCompany(
            @PathVariable Long id,
            @RequestParam String companyName) {

        Company company = companyService.update(id, companyName);

        ApiResponse<Company> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Company updated successfully");
        response.setData(company);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(
            @PathVariable Long id) {

        companyService.delete(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Company deleted successfully");

        return ResponseEntity.ok(response);
    }
}