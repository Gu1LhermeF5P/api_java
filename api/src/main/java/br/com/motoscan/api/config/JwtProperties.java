package br.com.motoscan.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt") // Diz ao Spring para buscar propriedades que começam com "jwt"
@Data // Lombok para getters e setters
public class JwtProperties {

    private String secret;
    private long expiration;

}
