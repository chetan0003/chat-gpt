package com.gpt.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class GPTRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String model;
    private String prompt;
    private double temperature;
    private int max_tokens;
    GPTRequest(String model, String prompt,
               double temperature, int max_tokens) {
        this.model = model;
        this.prompt = prompt;
        this.temperature = temperature;
        this.max_tokens = max_tokens;

    }

    public static GPTRequest defaultWith(String prompt) {
        return new GPTRequest("text-davinci-003", prompt, 0.7, 100);
    }
}
