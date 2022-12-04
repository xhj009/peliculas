package peliculas;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PeliculasApplication {

/*
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI();
	}

 */


	/*
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("bearer-key",
								new io.swagger.v3.oas.models.security.SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}

	 */


	/*
	@SecurityScheme(
			name = "Bearer Authentication",
			type = SecuritySchemeType.HTTP,
			bearerFormat = "JWT",
			scheme = "bearer"
	)

	@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
	@SecurityScheme(
			name = "bearerAuth",
			type = SecuritySchemeType.HTTP,
			bearerFormat = "JWT",
			scheme = "bearer"
	)

	 */




	/*
	@OpenAPIDefinition(
			info =@Info(
					title = "User API",
					version = "${api.version}",
					contact = @Contact(
							name = "Baeldung", email = "user-apis@baeldung.com", url = "https://www.baeldung.com"
					),
					license = @License(
							name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
					),
					termsOfService = "${tos.uri}",
					description = "${api.description}"
			),
			servers = @Server(
					url = "${api.server.url}",
					description = "Production"
			)
	)
*/

	public class OpenAPISecurityConfiguration {}

	@Bean
	public static ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(PeliculasApplication.class, args);
	}




}
