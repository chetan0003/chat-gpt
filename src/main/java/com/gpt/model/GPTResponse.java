package com.gpt.model;



import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
@Data
public class GPTResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Usage.Choice> choices;
    private Usage usage;

    GPTResponse(Usage usage, List<Usage.Choice> choices) {
      this.choices = choices;
      this.usage = usage;
    }

    public Optional<String> firstAnswer() {
        if (choices == null || choices.isEmpty())
            return Optional.empty();
        return Optional.of(choices.get(0).text);
    }

    @Data
    class Usage {
        private int total_tokens;
        private int prompt_tokens;
        private int completion_tokens;
        Usage(int total_tokens, int prompt_tokens, int completion_tokens) {
            this.total_tokens = total_tokens;
            this.prompt_tokens = prompt_tokens;
            this.completion_tokens = completion_tokens;

        }

        @Data
        class Choice {
            private String text;
            Choice(String text) {
             this.text = text;
            }

        }
    }

}
