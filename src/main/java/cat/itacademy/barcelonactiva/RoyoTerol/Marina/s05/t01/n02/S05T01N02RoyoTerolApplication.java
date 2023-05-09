package cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Flowers API", version = "1.0.0"),
		servers = {@Server(url = "http://localhost:9002")},
		tags = {@Tag(name = "Flowers Controller", description = "Manage the CRUD operations to create, read, update and delete flowers from the Database.")}
)
public class S05T01N02RoyoTerolApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(S05T01N02RoyoTerolApplication.class, args);
	}

}
