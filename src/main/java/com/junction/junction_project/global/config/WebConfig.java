package com.junction.junction_project.global.config;//package com.project.hiuni.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:8080",
                "http://localhost:3000",
                "http://localhost:5173",
                "https://api.junction-maverick.store",
                "https://www.junction-maverick.store",
                "https://junction-maverick.store",
                "https://junction-hackathon-maverick-fronten.vercel.app",
                "https://junction-maverick-fe.vercel.app",
                "https://junction-maverick-fe.vercel.app/",
                "https://junction-hackathon-maverick-fronten-steel.vercel.app/",
                "https://junction-hackathon-maverick-fronten-steel.vercel.app"
            )
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowCredentials(true)
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .maxAge(3600);
//    }
}