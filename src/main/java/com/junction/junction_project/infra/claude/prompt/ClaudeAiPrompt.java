package com.junction.junction_project.infra.claude.prompt;

import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ClaudeAiPrompt {
    public static String OCR_FORMMATING_PROMPT(String extractedText) {

        return """
            다음은 OCR로 추출한 텍스트입니다: %s
            
            지시사항:
            1. 각 체크리스트 항목을 title(질문/확인사항)과 description(설명/지침)으로 분리하세요
            2. title은 "~하셨나요?", "~했습니까?" 같은 확인 질문 형태로 만드세요
            3. description은 해당 항목의 이유나 상세 지침을 포함하세요
            
            중요:\s
            - 반드시 유효한 JSON 배열만 출력하세요
           - 마크다운 코드 블록(```)을 사용하지 마세요
           - "json"이라는 언어 표시를 하지 마세요
           - 추가 설명이나 주석을 포함하지 마세요
           - 첫 번째 문자는 반드시 '['이어야 하고 마지막 문자는 ']'이어야 합니다
            
           출력 형식 예시:
          [{"title":"안전모 착용 하셨나요?","description":"안전모를 착용해야 업무에 투입이 가능합니다"}]
            
          위 지시사항에 따라 순수 JSON 배열만 반환하세요:
          """.formatted(extractedText);

    }
}
