package com.dev.track.Services.Implementation;

import com.dev.track.DTO.ProblemDto;
import com.dev.track.Entity.Company;
import com.dev.track.Entity.Problem;
import com.dev.track.Entity.Topic;
import com.dev.track.Exception.ResourceNotFoundException;
import com.dev.track.Mapper.ProblemMapper;
import com.dev.track.Repository.CompanyRepository;
import com.dev.track.Repository.ProblemRepository;
import com.dev.track.Repository.TopicRepository;
import com.dev.track.Services.ProblemService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ImplProblem implements ProblemService {
    private final ProblemRepository problemRepository;
    private final CompanyRepository companyRepository;
    private final TopicRepository topicRepository;
    private final ProblemMapper problemMapper;

    ImplProblem(ProblemRepository problemRepository, CompanyRepository companyRepository, TopicRepository topicRepository, ProblemMapper problemMapper) {
        this.problemRepository = problemRepository;
        this.companyRepository = companyRepository;
        this.topicRepository = topicRepository;
        this.problemMapper = problemMapper;
    }
    @Override
    public ProblemDto create(ProblemDto problemDto) {
        Company company = companyRepository.findById(problemDto.getCompanyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company not found with id: " + problemDto.getCompanyId()
                        ));

        Topic topic = topicRepository.findById(problemDto.getTopicId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Topic not found with id: " + problemDto.getTopicId()
                        ));

        Problem problem = problemMapper.toEntity(problemDto);

        problem.setCompany(company);
        problem.setTopic(topic);

        Problem savedProblem = problemRepository.save(problem);

        return problemMapper.toDto(savedProblem);
    }

    @Override
    public ProblemDto update(Long id, ProblemDto problemDto) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Problem not found with id: " + id));

        Company company = companyRepository.findById(problemDto.getCompanyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company not found with id: " + problemDto.getCompanyId()
                        ));

        Topic topic = topicRepository.findById(problemDto.getTopicId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Topic not found with id: " + problemDto.getTopicId()
                        ));

        problem.setCompany(company);
        problem.setTopic(topic);
        problem.setDifficulty(problemDto.getDifficulty());
        problem.setNotes(problemDto.getNotes());
        problem.setProblemLink(problemDto.getProblemLink());
        problem.setProblemStatus(problemDto.getProblemStatus());
        problem.setProblemTitle(problemDto.getProblemTitle());
        problem.setPlatformName(problemDto.getPlatformName());

        Problem savedProblem = problemRepository.save(problem);

        return problemMapper.toDto(savedProblem);

    }

    @Override
    public void delete(Long id) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Problem not found with id: " + id));

        problemRepository.delete(problem);
    }

    @Override
    public List<ProblemDto> findAll() {
        return problemRepository.findAll()
                .stream()
                .map(problemMapper::toDto)
                .toList();
    }
}
