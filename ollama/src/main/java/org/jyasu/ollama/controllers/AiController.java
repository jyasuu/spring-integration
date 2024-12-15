package org.jyasu.ollama.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class AiController {
    
    private final OllamaChatModel chatModel;

    @Value("classpath:prompts/1.st")
	private Resource templateResource;

    @GetMapping("/ai/generate")
    public Map<String,String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", this.chatModel.call(message));
    }

    @GetMapping("/ai/generateWithRole")
    public Map<String,String> generateWithRole(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        
    List<Message> messages = List.of(
        new SystemMessage("You are a master storyteller. If you can't find information or recent news, just make up a news that sounds happy."),
        new UserMessage(message)
        );
    ChatResponse response = chatModel
            .call(new Prompt(
                messages,
                OllamaOptions.builder()
                .withTemperature(1d)
                                .build()
            ));
        return Map.of("generation", response.getResult().getOutput().getContent());
    }

    @GetMapping("/ai/template")
    public Flux<String> template(@RequestParam(value = "message", defaultValue = "openai") String message) {
        
        String template = "May I ask what models {llm} currently has and what special abilities each has?";
        PromptTemplate promptTemplate = new PromptTemplate(template);
        promptTemplate = new PromptTemplate(templateResource);
        Prompt prompt = promptTemplate.create(Map.of("llm", message));
        return this.chatModel.stream(prompt).map(res -> res.getResult().getOutput().getContent());
    }

    @GetMapping(value = "/ai/generateStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return this.chatModel.stream(prompt).map(res -> res.getResult().getOutput().getContent());
    }

	@GetMapping("/chat")
	public String chat(String prompt) {
        
        ChatResponse response = chatModel.call(
            new Prompt(
                "Just output a pure JSON object and not with mardown code block like ``` for Generate the names of 5 famous pirates.",
                OllamaOptions.builder()
                    .withModel(OllamaModel.LLAMA3_1)
                    .withTemperature(0.4)
                    .build()
            ));
		return response.getResult().getOutput().getContent();
	}
}