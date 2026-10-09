package com.dev.track.Controller;


import com.dev.track.DTO.LeetCodeProblemListResponse;
import com.dev.track.Services.LeetCodeClient;
import com.dev.track.Services.LeetCodeSyncService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leetcode/test")
public class LeetCodeTestController {
    private final LeetCodeClient leetCodeClient;
    private final LeetCodeSyncService leetCodeSyncService;

    public LeetCodeTestController(LeetCodeClient leetCodeClient,  LeetCodeSyncService leetCodeSyncService) {
        this.leetCodeClient = leetCodeClient;
        this.leetCodeSyncService = leetCodeSyncService;
    }

    @GetMapping("/questions")
    public List<LeetCodeProblemListResponse.Question> getQuestions() {
        return leetCodeClient.fetchQuestions(0, 10);
    }

    @PostMapping("/sync")
    public String syncBatch(
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "10") int limit) {

        int synced = leetCodeSyncService.syncBatch(skip, limit);
        return "Processed " + synced + " LeetCode problems.";
    }

    @PostMapping("/sync-all")
    public String syncAll() {
        int processed = leetCodeSyncService.syncAll();
        return "Processed " + processed + " LeetCode problems.";
    }

}
