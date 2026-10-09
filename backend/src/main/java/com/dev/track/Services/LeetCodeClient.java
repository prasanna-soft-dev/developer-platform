package com.dev.track.Services;

import com.dev.track.DTO.LeetCodeGraphQLRequest;
import com.dev.track.DTO.LeetCodeProblemListResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class LeetCodeClient {

    private final RestClient restClient;

    private static final String GRAPHQL_QUERY = """
        query problemsetQuestionList(
            $categorySlug: String,
            $limit: Int,
            $skip: Int,
            $filters: QuestionListFilterInput
        ) {
            problemsetQuestionList: questionList(
                categorySlug: $categorySlug,
                limit: $limit,
                skip: $skip,
                filters: $filters
            ) {
                total: totalNum
                questions: data {
                    frontendQuestionId: questionFrontendId
                    title
                    titleSlug
                    difficulty
                    paidOnly: isPaidOnly
                    status
                    acRate
                    topicTags {
                        name
                        slug
                    }
                }
            }
        }
        """;

    public LeetCodeClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://leetcode.com")
                .build();
    }

    public List<LeetCodeProblemListResponse.Question> fetchQuestions(
            int skip, int limit) {

        Map<String, Object> variables = Map.of(
                "categorySlug", "",
                "skip", skip,
                "limit", limit,
                "filters", Map.of()
        );

        LeetCodeGraphQLRequest request =
                new LeetCodeGraphQLRequest(GRAPHQL_QUERY, variables);

        LeetCodeProblemListResponse response = restClient.post()
                .uri("/graphql")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(LeetCodeProblemListResponse.class);

        if (response == null
                || response.getData() == null
                || response.getData().getProblemList() == null
                || response.getData().getProblemList().getQuestions() == null) {
            throw new IllegalStateException(
                    "Invalid or empty response received from LeetCode");
        }

        return response.getData()
                .getProblemList()
                .getQuestions();
    }
}