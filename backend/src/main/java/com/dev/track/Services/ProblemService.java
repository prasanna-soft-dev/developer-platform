package com.dev.track.Services;

import com.dev.track.DTO.ProblemDto;

import com.dev.track.Entity.Problem;

import java.util.List;

public interface ProblemService {

    ProblemDto create(ProblemDto problemDto);

    ProblemDto update(Long id, ProblemDto problemDto);

    void delete(Long id);

    List<ProblemDto> findAll();
}
