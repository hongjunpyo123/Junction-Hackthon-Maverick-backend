package com.junction.junction_project.infra.claude.prompt;

import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ClaudeAiPrompt {
    public static String OCR_FORMMATING_PROMPT(String extractedText) {

        return """
            The following is text extracted via OCR: %s
            
            Instructions:
            1. Separate each checklist item into title (question/confirmation) and description (explanation/guidelines)
            2. Make the title in the form of confirmation questions like "Did you...?", "Have you...?"
            3. Include reasons or detailed guidelines for each item in the description
            
            Important:
            - Output only valid JSON array
            - Do not use markdown code blocks (```)
            - Do not include language indicators like "json"
            - Do not include additional explanations or comments
            - First character must be '[' and last character must be ']'
            
            Output format example:
            [{"title":"Did you wear your safety helmet?","description":"Safety helmet must be worn to be allowed to work"}]
            
            Return only pure JSON array according to the above instructions:
            """.formatted(extractedText);
    }
}
