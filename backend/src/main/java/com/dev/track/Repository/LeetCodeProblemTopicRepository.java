package com.dev.track.Repository;

import com.dev.track.Entity.LeetCodeProblemTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeetCodeProblemTopicRepository extends JpaRepository<LeetCodeProblemTopic, Long> {
    List<LeetCodeProblemTopic> findByProblem_Id(Long problemId);

    @Modifying
    @Query("DELETE FROM LeetCodeProblemTopic pt WHERE pt.problem.id = :problemId")
    void deleteByProblemId(@Param("problemId") Long problemId);



    @Query("""
    SELECT pt.problem.id
    FROM LeetCodeProblemTopic pt
    WHERE pt.topic.slug = :topicSlug
""")
    List<Long> findProblemIdsByTopicSlug(
            @Param("topicSlug") String topicSlug
    );
}
