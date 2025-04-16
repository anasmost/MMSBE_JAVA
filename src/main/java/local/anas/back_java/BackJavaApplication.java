package local.anas.back_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWarDeployment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
@ConditionalOnNotWarDeployment
public class BackJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackJavaApplication.class, args);
	}

}
