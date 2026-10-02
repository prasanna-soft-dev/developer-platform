package com.dev.track.Controller;

import com.dev.track.DTO.ApiResponse;
import com.dev.track.Entity.Topic;
import com.dev.track.Services.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Topic>> createTopic(
            @RequestParam String topicName) {

        Topic topic = topicService.create(topicName);

        ApiResponse<Topic> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Topic created successfully");
        response.setData(topic);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Topic>>> getTopics() {

        List<Topic> topics = topicService.findAll();

        ApiResponse<List<Topic>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Topics fetched successfully");
        response.setData(topics);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Topic>> updateTopic(
            @PathVariable Long id,
            @RequestParam String topicName) {

        Topic topic = topicService.update(id, topicName);

        ApiResponse<Topic> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Topic updated successfully");
        response.setData(topic);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTopic(
            @PathVariable Long id) {

        topicService.delete(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Topic deleted successfully");

        return ResponseEntity.ok(response);
    }
}