package one.dio.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProjetoSpringPadraoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringPadraoApplication.class, args);
	}

}
