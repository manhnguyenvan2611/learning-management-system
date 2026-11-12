package com.learningmanagementsystem.learning_management_system.service;

import java.util.*;

import org.springframework.stereotype.Service;

import tools.jackson.databind.ObjectMapper;

@Service
public class ServiceMiddleware {
    public Map<String, String> convertStringToMapString(String str) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> objMap = objectMapper.readValue(str, Map.class);

        return objMap;
    }

    public Map<String, List<Map<String, String>>> convertStringToObjectMapList(String str) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, List<Map<String, String>>> objMap = objectMapper.readValue(str, Map.class);

        return objMap;
    }
}
