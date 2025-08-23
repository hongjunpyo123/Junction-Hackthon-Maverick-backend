package com.junction.junction_project.infra.claude;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClaudeAiClient {

  private final ChatClient chatClient;

  public String call(String prompt) {
    return chatClient.prompt()
        .user(prompt)
        .call()
        .content();
  }
}