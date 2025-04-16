package local.anas.back_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWarDeployment;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
@ConditionalOnWarDeployment
public class BackJavaApplicationWar extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(BackJavaApplicationWar.class, args);
  }
}
