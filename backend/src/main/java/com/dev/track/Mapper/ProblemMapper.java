package com.dev.track.Mapper;

import com.dev.track.DTO.ProblemDto;
import com.dev.track.Entity.Problem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProblemMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "topic.id", target = "topicId")
    ProblemDto toDto(Problem problem);

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "topic", ignore = true)
    Problem toEntity(ProblemDto problemDto);
}