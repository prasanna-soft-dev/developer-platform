package com.dev.track.Entity;

import com.dev.track.Enum.Difficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "leetcode_problem",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_leetcode_problem_slug",
                        columnNames = {"title_slug"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_leetcode_problem_difficulty",
                        columnList = "difficulty"
                ),
                @Index(
                        name = "idx_leetcode_problem_paid_only",
                        columnList = "paid_only"
                )
        }
)
public class LeetCodeProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "frontend_question_id", length = 20)
    private String frontendQuestionId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "title_slug", nullable = false)
    private String titleSlug;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false, length = 10)
    private Difficulty difficulty;

    @Column(name = "paid_only", nullable = false)
    private boolean paidOnly;

    @Column(name = "acceptance_rate")
    private Double acceptanceRate;

    @Column(name = "problem_url", nullable = false, length = 500)
    private String problemUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "last_synced_at")
    private Instant lastSyncedAt;
}