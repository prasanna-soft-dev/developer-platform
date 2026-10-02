package com.dev.track.Controller;

import com.dev.track.DTO.ApiResponse;
import com.dev.track.DTO.ProblemDto;
import com.dev.track.Services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemService problemService;


    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProblemDto>> createProblem(
            @RequestBody ProblemDto problemDto) {

        ProblemDto createdProblem = problemService.create(problemDto);

        ApiResponse<ProblemDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Problem created successfully");
        response.setData(createdProblem);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProblemDto>>> getProblems() {

        List<ProblemDto> problems = problemService.findAll();

        ApiResponse<List<ProblemDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Problems fetched successfully");
        response.setData(problems);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProblemDto>> updateProblem(
            @PathVariable Long id,
            @RequestBody ProblemDto problemDto) {

        ProblemDto updatedProblem =
                problemService.update(id, problemDto);

        ApiResponse<ProblemDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Problem updated successfully");
        response.setData(updatedProblem);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProblem(
            @PathVariable Long id) {

        problemService.delete(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Problem deleted successfully");

        return ResponseEntity.ok(response);
    }
}