package yang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-10 18:14
 **/
@Configuration
public class BlogConfig extends WebMvcConfigurerAdapter {
    @Override
    // 解决ajax、JQ不能跨域的问题
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }
}
