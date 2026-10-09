
package com.dev.track.Repository;

import com.dev.track.Entity.LeetCodeProblem;
import com.dev.track.Enum.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeetCodeProblemRepository
        extends JpaRepository<LeetCodeProblem, Long> {

    Optional<LeetCodeProblem> findByTitleSlug(String titleSlug);

    boolean existsByTitleSlug(String titleSlug);

    Page<LeetCodeProblem> findByDifficulty(
            Difficulty difficulty,
            Pageable pageable
    );

    Page<LeetCodeProblem> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );


    Page<LeetCodeProblem> findByDifficultyAndTitleContainingIgnoreCase(
            Difficulty difficulty,
            String title,
            Pageable pageable
    );


    @Query("""
    SELECT DISTINCT p
    FROM LeetCodeProblem p
    JOIN LeetCodeProblemTopic pt ON pt.problem.id = p.id
    JOIN pt.topic t
    WHERE t.slug = :topicSlug
      AND (:difficulty IS NULL OR p.difficulty = :difficulty)
      AND (:title IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')))
""")
    Page<LeetCodeProblem> searchByTopicAndFilters(
            @Param("topicSlug") String topicSlug,
            @Param("difficulty") Difficulty difficulty,
            @Param("title") String title,
            Pageable pageable
    );
}