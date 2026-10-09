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
        name="leetcode_topic",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_leetcode_topic_slug",
                        columnNames = {"slug"}
                )

        }
)

public class LeetCodeTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String slug;


}
