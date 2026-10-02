package com.dev.track.Entity;


import com.dev.track.Enum.Difficulty;
import com.dev.track.Enum.Status;
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
@Table(name = "problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "topic_id")
    private Topic topic;

    @Column(nullable = false, name = "problem_title")
    private String problemTitle;

    @Column(name = "problem_link")
    private String problemLink;

    @Column(nullable = false, name = "platform_name")
    private String platformName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "difficulty")
    private Difficulty difficulty;

    @Column(name = "notes")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "problem_status")
    private Status problemStatus;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant created;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updated;
}
