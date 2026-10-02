package com.dev.track.DTO;

import com.dev.track.Entity.Company;
import com.dev.track.Entity.Topic;
import com.dev.track.Enum.Difficulty;
import com.dev.track.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDto {

    private Long id;

    private Long companyId;

    private Long topicId;

    private String problemTitle;

    private String problemLink;

    private String platformName;

    private Difficulty difficulty;

    private String notes;

    private Status problemStatus;
}
