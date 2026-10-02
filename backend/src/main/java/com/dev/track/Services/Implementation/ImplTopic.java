package com.dev.track.Services.Implementation;

import com.dev.track.Entity.Topic;
import com.dev.track.Exception.DuplicateResourceException;
import com.dev.track.Exception.ResourceNotFoundException;
import com.dev.track.Repository.TopicRepository;
import com.dev.track.Services.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplTopic implements TopicService {
    private final TopicRepository topicRepository;

    ImplTopic(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
    @Override
    public Topic create(String topicName) {
        Optional<Topic> optional = topicRepository.findByTopicName(topicName);

        if (optional.isPresent()) {
            throw new DuplicateResourceException("Topic with name '" + topicName + "' already exists");
        }

        Topic topic = new Topic();
        topic.setTopicName(topicName);
        return topicRepository.save(topic);
    }

    @Override
    public Topic update(Long id, String topicName) {
        Topic topic = topicRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Topic is not found" + id));
        topic.setTopicName(topicName);
        return topicRepository.save(topic);
    }

    @Override
    public void delete(Long id) {
        Topic topic = topicRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Topic is not found" + id));
        topicRepository.delete(topic);
    }

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }
}
