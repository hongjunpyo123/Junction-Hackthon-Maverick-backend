package com.junction.junction_project.infra.claude.prompt;

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
            
            ## Base Safety Categories and Mandatory Requirements:
            
            ### 1. Personal Protective Equipment (PPE)
            - Mandatory for ALL construction sites regardless of type
            - Must include: hard hats, safety shoes, safety harnesses, protective clothing
            
            ### 2. Fall Prevention (High-rise Work)
            - Required when building height exceeds 2 floors OR total households > 10
            - Must include: guardrails, safety nets, fall arrest systems
            
            ### 3. Electrical Safety
            - Mandatory for ALL construction sites
            - Must include: GFCI installation, electrical equipment inspection
            
            ### 4. Structural Safety
            - Required for: reinforced concrete, steel structures, major renovations
            - Must include: temporary support systems, structural integrity checks
            
            ### 5. Fire Safety
            - Required for: commercial use, residential buildings with >5 households
            - Must include: fire extinguishers, evacuation routes, flammable material management
            
            ### 6. Heavy Equipment Safety
            - Required for: new construction, building area >500㎡
            - Must include: crane inspections, equipment operator certification
            
            ### 7. Hazardous Material Management
            - Required for: renovation/demolition of existing buildings, change of use
            - Must include: asbestos surveys, lead paint testing, proper disposal
            
            ### 8. Work Permit System
            - Required for: welding work, confined spaces, high-risk operations
            - Must include: hot work permits, confined space entry permits
            
            ## Instructions:
            1. Analyze the provided building data
            2. Select relevant safety categories based on: permit type, primary use, structure type, building scale, and project characteristics
            3. Generate specific safety checklist items for the selected categories
            4. Each checklist item must be practical and actionable
            5. Include legal basis references where applicable (Korean safety regulations)
            
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
            - title: Brief checklist item title
            - description: Detailed explanation of what needs to be checked
            
            Example format:
            [
             {
               "title": "Hard Hat Inspection",
               "description": "Verify all workers are wearing properly fitted hard hats that meet safety standards before entering the construction site"
             }
            ]
            
            Now analyze the following building data and generate an appropriate safety checklist:
            
            
            """.formatted(addressesWithInfoDTO.getKoreanAddress(), addressesWithInfoDTO.getPrimaryUse(),
            addressesWithInfoDTO.getPrimaryUse(), addressesWithInfoDTO.getLandUseZone(),
            addressesWithInfoDTO.getTotalNumOfHouseholds(), addressesWithInfoDTO.getPrimaryStructure(),
            addressesWithInfoDTO.getRoofStructure(), addressesWithInfoDTO.getFARCalculationFloorArea());

    }
}
