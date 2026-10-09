package com.dev.track.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeetCodeGraphQLRequest {
    private String query;
    private Map<String, Object> variables;
}
