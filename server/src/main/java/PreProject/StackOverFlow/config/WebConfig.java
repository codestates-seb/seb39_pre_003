package PreProject.StackOverFlow.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로
//                .allowedOrigins("http://localhost:3000") // 해당 origin 허용
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PATCH", "DELETE"); // 허용할 Request 메서드
    }
}
