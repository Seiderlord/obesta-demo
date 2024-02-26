package at.example.backenddemo.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyCloakAuthoritiesExtractor implements Converter<Jwt, AbstractAuthenticationToken> {

    private final String clientId;
    public KeyCloakAuthoritiesExtractor(String clientId) {
        this.clientId = clientId;
    }

    protected List<GrantedAuthority> extractAuthorities(final Jwt source){
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        final Map<String,Object> claims = source.getClaims();

        if(claims.containsKey("realm_access")) {
            final Map<String, Object> realm_access = (Map<String, Object>) claims.get("realm_access");
            grantedAuthorities.addAll(getRoles(realm_access));
        }
        if(claims.containsKey("resource_access")){
            final Map<String,Object> resourceAccess = (Map<String, Object>) claims.get("resource_access");
            final Map<String,Object> client = (Map<String, Object>) resourceAccess.get(this.clientId);
            if(client != null){
                grantedAuthorities.addAll(getRoles(client));
            }
        }
        return grantedAuthorities;
    }

    private List<SimpleGrantedAuthority> getRoles(final Map<String, Object> client){
        final Collection<String> clientRoles = (Collection<String>) client.get("roles");
        final List<SimpleGrantedAuthority> realRolesList = clientRoles.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return realRolesList;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        final Collection<GrantedAuthority> authorities = this.extractAuthorities(source);

        final String principalClaimValue = source.getClaimAsString(JwtClaimNames.SUB);
        return new JwtAuthenticationToken(source, authorities, principalClaimValue);
    }
}
