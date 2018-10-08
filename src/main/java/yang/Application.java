package yang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 16:23
 **/
@SpringBootApplication
@EnableScheduling
public class Application implements EmbeddedServletContainerCustomizer {

    public static Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        logger.error("Hello World");
        SpringApplication.run(Application.class, args);
        logger.error("Link Start!");
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(9090);
    }
}
