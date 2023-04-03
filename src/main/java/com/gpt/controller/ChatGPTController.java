package com.gpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpt.model.GPTRequest;
import com.gpt.model.GPTResponse;
import com.gpt.model.Message;
import com.gpt.configuration.OpenAiApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ask")
@RequiredArgsConstructor
@RestController
public class ChatGPTController {

    private final ObjectMapper jsonMapper;
    private final OpenAiApiClient client;


    @PostMapping()
    public ResponseEntity<String> chat(@RequestBody Message message) {
        String response = null;
        try {
             response = chatWithGpt3(message.getMsg());
        } catch (Exception e) {
          System.out.println("error while calling chatGpt " +e.getMessage());
          return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    private String chatWithGpt3(String prompt) throws Exception {
        var completion = GPTRequest.defaultWith(prompt);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson);
        var completionResponse = jsonMapper.readValue(responseBody, GPTResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }
}
