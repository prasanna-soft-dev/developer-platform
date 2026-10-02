package com.dev.track.Services;

import com.dev.track.Entity.Topic;

import java.util.List;

public interface TopicService {
    Topic create(String topicName);
    Topic update(Long id, String topicName);
    void delete(Long id);
    List<Topic> findAll();
}
