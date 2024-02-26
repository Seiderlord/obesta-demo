package at.example.backenddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
@SpringBootApplication
@SecurityScheme(type = SecuritySchemeType.OPENIDCONNECT, name = "openId", openIdConnectUrl = "<INSERT-YOUR-AUTH-SERVER-ADDRESS>/realms/<INSERT-YOUR-REALM>/.well-known/openid-configuration")
@SecurityScheme(name = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@OpenAPIDefinition(
        info = @Info(title = "Demo App", version = "1.0"),
        security = {@SecurityRequirement(name = "openId"), @SecurityRequirement(name = "bearer")},
        servers = {
                @Server(url = "<INSERT-YOUR-SERVER-ADDRESS>", description = "Default Server URL")
        }
)
public class BackendDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendDemoApplication.class, args);
    }

}
