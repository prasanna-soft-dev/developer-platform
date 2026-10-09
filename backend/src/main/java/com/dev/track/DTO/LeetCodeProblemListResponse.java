package com.dev.track.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LeetCodeProblemListResponse{

    private DataWrapper data;

    @Data
    public static class DataWrapper {

        @JsonProperty("problemsetQuestionList")
        private ProblemList problemList;
    }

    @Data
    public static class ProblemList {

        private Integer total;

        private List<Question> questions;
    }

    @Data
    public static class Question {

        private String frontendQuestionId;
        private String title;
        private String titleSlug;
        private String difficulty;

        @JsonProperty("paidOnly")
        private boolean paidOnly;

        private Double acRate;
        private List<TopicTag> topicTags;
    }

    @Data
    public static class TopicTag {
        private String name;
        private String slug;
    }
}
