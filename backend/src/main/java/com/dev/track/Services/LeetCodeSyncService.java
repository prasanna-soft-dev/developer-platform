package com.dev.track.Services;

import com.dev.track.DTO.LeetCodeProblemListResponse;
import com.dev.track.Entity.LeetCodeProblem;
import com.dev.track.Entity.LeetCodeProblemTopic;
import com.dev.track.Entity.LeetCodeTopic;
import com.dev.track.Enum.Difficulty;
import com.dev.track.Repository.LeetCodeProblemRepository;
import com.dev.track.Repository.LeetCodeProblemTopicRepository;
import com.dev.track.Repository.LeetCodeTopicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Locale;

@Service
public class LeetCodeSyncService {

    private final LeetCodeClient leetCodeClient;
    private final LeetCodeProblemRepository repository;
    private final LeetCodeTopicRepository topicRepository;
    private final LeetCodeProblemTopicRepository problemTopicRepository;

    public LeetCodeSyncService(
            LeetCodeClient leetCodeClient,
            LeetCodeProblemRepository repository,
            LeetCodeTopicRepository topicRepository,
            LeetCodeProblemTopicRepository problemTopicRepository) {
        this.leetCodeClient = leetCodeClient;
        this.repository = repository;
        this.topicRepository = topicRepository;
        this.problemTopicRepository = problemTopicRepository;

    }

    @Transactional
    public int syncBatch(int skip, int limit) {

        if (skip < 0 || limit < 1 || limit > 100) {
            throw new IllegalArgumentException(
                    "skip must be non-negative and limit must be between 1 and 100");
        }

        List<LeetCodeProblemListResponse.Question> questions =
                leetCodeClient.fetchQuestions(skip, limit);

        for (LeetCodeProblemListResponse.Question question : questions) {

            LeetCodeProblem problem = repository
                    .findByTitleSlug(question.getTitleSlug())
                    .orElseGet(LeetCodeProblem::new);

            problem.setFrontendQuestionId(question.getFrontendQuestionId());
            problem.setTitle(question.getTitle());
            problem.setTitleSlug(question.getTitleSlug());

            problem.setDifficulty(
                    Difficulty.valueOf(
                            question.getDifficulty().toUpperCase(Locale.ROOT)
                    )
            );

            problem.setPaidOnly(question.isPaidOnly());
            problem.setAcceptanceRate(question.getAcRate());
            problem.setProblemUrl(
                    "https://leetcode.com/problems/"
                            + question.getTitleSlug() + "/"
            );
            problem.setLastSyncedAt(Instant.now());

            LeetCodeProblem savedProblem = repository.save(problem);

            problemTopicRepository.deleteByProblemId(savedProblem.getId());

            if (question.getTopicTags() != null) {
                for (LeetCodeProblemListResponse.TopicTag tag : question.getTopicTags()) {

                    LeetCodeTopic topic = topicRepository
                            .findBySlug(tag.getSlug())
                            .orElseGet(() -> {
                                LeetCodeTopic newTopic = new LeetCodeTopic();
                                newTopic.setName(tag.getName());
                                newTopic.setSlug(tag.getSlug());
                                return topicRepository.save(newTopic);
                            });

                    LeetCodeProblemTopic mapping = new LeetCodeProblemTopic();
                    mapping.setProblem(savedProblem);
                    mapping.setTopic(topic);

                    problemTopicRepository.save(mapping);
                }
            }
        }

        return questions.size();
    }

    @Transactional
    public int syncAll() {
        int skip = 0;
        int batchSize = 100;
        int totalSynced = 0;

        while (true) {
            int synced = syncBatch(skip, batchSize);

            totalSynced += synced;

            if (synced < batchSize) {
                break;
            }

            skip += batchSize;
        }

        return totalSynced;
    }
}