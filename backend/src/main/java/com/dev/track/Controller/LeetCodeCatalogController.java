
package com.dev.track.Controller;

import com.dev.track.Entity.LeetCodeProblem;
import com.dev.track.Repository.LeetCodeProblemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.track.Enum.Difficulty;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/leetcode/problems")
public class LeetCodeCatalogController {

    private final LeetCodeProblemRepository repository;

    public LeetCodeCatalogController(
            LeetCodeProblemRepository repository) {
        this.repository = repository;
    }



    @GetMapping
    public Page<LeetCodeProblem> getProblems(
            @RequestParam(required = false) Difficulty difficulty,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String topic,
            @PageableDefault(
                    size = 20,
                    sort = "title",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        if (topic != null && !topic.isBlank()) {
            return repository.searchByTopicAndFilters(
                    topic,
                    difficulty,
                    title != null && !title.isBlank() ? title : null,
                    pageable
            );
        }

        if (difficulty != null && title != null && !title.isBlank()) {
            return repository.findByDifficultyAndTitleContainingIgnoreCase(
                    difficulty, title, pageable
            );
        }

        if (difficulty != null) {
            return repository.findByDifficulty(difficulty, pageable);
        }

        if (title != null && !title.isBlank()) {
            return repository.findByTitleContainingIgnoreCase(title, pageable);
        }

        return repository.findAll(pageable);
    }
}