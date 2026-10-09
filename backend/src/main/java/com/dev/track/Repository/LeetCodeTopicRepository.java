
package com.dev.track.Repository;

import com.dev.track.Entity.LeetCodeTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeetCodeTopicRepository
        extends JpaRepository<LeetCodeTopic, Long> {

    Optional<LeetCodeTopic> findBySlug(String slug);
}