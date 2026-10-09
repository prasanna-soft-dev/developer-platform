package com.dev.track.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "leetcode_problem_topic",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_leetcode_problem_topic",
                columnNames = {"problem_id", "topic_id"}
        )
            },
        indexes ={
        @Index(
                name = "idx_leetcode_problem_topic_topic",
                columnList = "topic_id"
        )
        }
)
public class LeetCodeProblemTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "problem_id", nullable = false)
    private LeetCodeProblem problem;

    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private LeetCodeTopic topic;
}
