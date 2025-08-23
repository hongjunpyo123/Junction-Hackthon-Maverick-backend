package com.junction.junction_project.infra.claude.prompt;

import com.junction.junction_project.domain.SafetyAssessment.dto.SafetyAssessmentRequest;
import com.junction.junction_project.domain.checklist.dto.AddressesWithInfoDTO;
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

    public static String GENERATE_CHECKLIST_PROMPT(AddressesWithInfoDTO addressesWithInfoDTO) {

        return """
            
            You are a construction safety expert specializing in Korean building regulations and safety management. Your task is to generate a safety checklist for construction sites based on provided building permit data.
            
            Based on the following regulations:
            - Rules on Occupational Safety and Health Standards
            - Enforcement Decree of the Construction Technology Promotion Act
            - Enforcement Decree of the Serious Accidents Punishment Act
            
            The following mandatory inspection items are required. When generating the checklist, you must include these items.
            
            The checklist format must follow: [Category / Item / Yes (Compliant) & No (Non-compliant)].
            Create only one item per category.
            Keep sentence length short and consistent across items for clarity.
            
            ## Mandatory Safety Categories:
            
            ### Category: Personal Protective Equipment (PPE)
            Example: Use of safety helmet, safety belt, and safety shoes
            Reference: Occupational Safety and Health Standards Article 34
            
            ### Category: Work at Height
            Example: Fall prevention measures for work over 2m (guardrails, safety nets, fall arrest systems)
            Reference: Occupational Safety and Health Standards Article 38
            
            ### Category: Electrical Safety
            Example: Residual current device installed, no electrical defects
            Reference: Occupational Safety and Health Standards Article 326
            
            ### Category: Falling Objects Prevention
            Example: Safety nets installed, materials secured against dropping
            Reference: Occupational Safety and Health Standards Article 274
            
            ### Category: Temporary Structures
            Example: Scaffolding and shoring properly fixed and stable
            Reference: Occupational Safety and Health Standards Articles 206–209
            
            ### Category: Equipment Use
            Example: Regular inspection records of cranes and aerial lifts
            Reference: Construction Machinery Management Act, Occupational Safety and Health Standards
            
            ### Category: Safety Training
            Example: Training provided for new employees and high-risk workers
            Reference: Occupational Safety and Health Act Article 29
            
            ### Category: Work Permit
            Example: Permits issued for welding, confined space, and other high-risk tasks
            Reference: Occupational Safety and Health Standards Article 50
            
            ## Instructions:
            1. Analyze the provided building data
            2. Generate exactly ONE checklist item for each of the 8 mandatory categories above
            3. Each checklist item must be practical, actionable, and suitable for Yes/No evaluation
            4. Keep descriptions short and consistent in length across all items
            5. Adapt the examples above to match the specific building characteristics provided
            
            ## Input Data Format:
            - koreanAddress: %s
            - permitType: %s
            - primaryUse: %s
            - landUseZone: %s
            - totalNumOfHouseholds: %s
            - primaryStructure: %s
            - roofStructure: %s
            - FARCalculationFloorArea: %s
            
            ## Required Response Format:
            Return ONLY a JSON array with no markdown formatting. Each item must have exactly these two string fields:
            - title: Brief checklist item title (corresponding to the category)
            - description: Short, clear explanation suitable for Yes/No compliance check
            
            Example format:
            [
            {
              "title": "Personal Protective Equipment Compliance",
              "description": "All workers wearing required safety helmet, safety belt, and safety shoes"
            }
            ]
            
            **Remember: Use ONLY English language in your response. No Korean text allowed.**
            **Important: Generate exactly 8 items, one for each mandatory category listed above.**
            
            Now analyze the following building data and generate an appropriate safety checklist:
            
            
            """.formatted(addressesWithInfoDTO.getKoreanAddress(), addressesWithInfoDTO.getPrimaryUse(),
            addressesWithInfoDTO.getPrimaryUse(), addressesWithInfoDTO.getLandUseZone(),
            addressesWithInfoDTO.getTotalNumOfHouseholds(), addressesWithInfoDTO.getPrimaryStructure(),
            addressesWithInfoDTO.getRoofStructure(), addressesWithInfoDTO.getFARCalculationFloorArea());

    }

    public static String SAFETY_ASSESSMENT(SafetyAssessmentRequest safetyAssessmentRequest) {
        return
            """
                 You are an AI safety assessor for construction sites.
                     Based on the inputs below, calculate the Safety Score (0–100) and Risk Level () for the site. 
                
                     1. Number of workers: {≤10 / ≤30 / ≤50}
                       - Fewer workers = higher risk, apply larger deductions.
                
                     2. Safety checklist: [Category / Item / true or false]
                       - true = compliant, no deduction\s
                       - false = non-compliant, apply deduction (critical items = higher penalty)
                
                     [Scoring Rules]
                     - Base score: 100
                     - Worker count: ≤10 = -20, ≤30 = -10, ≤50 = -5
                     - Each "false" response deducts points based on category weight
                     - Safety Score = 100 - total deductions
                     - Risk Level () = 100 - Safety Score
                
                     [input data]
                     - Number of workers : %d
                     - Checklist Information : %s
                
                     [Output Format]
                     Provide your assessment in JSON format only:
                     {
                      "safetyScore": [0-100 integer],
                      "riskScore": [0-100 integer],
                      "issues": "[summary of non-compliant items or 'No safety violations identified']"
                     }
                
                     Requirements:
                     - Always respond in English
                     - safetyScore + riskScore must equal 100
                     - Summarize all "false" responses in the issues field as one sentence
                     - IMPORTANT: Return only raw JSON without any markdown formatting, backticks, or code blocks
                     - Do not use ``` or ```json in your response
            
            
            """.formatted(safetyAssessmentRequest.getNumOfWorkers(), safetyAssessmentRequest.getAddressInfoList());
    }
}
