package linuwux.xyz.desafio_java_backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = { @Server(url="/", description = "Default server url") } )

@SpringBootApplication
public class DesafioJavaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioJavaBackendApplication.class, args);
	}

}
