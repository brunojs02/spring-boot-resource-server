package com.brunojs02.resourceserver.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

	@Override
	public void configure(JwtAccessTokenConverter converter) {
		converter.setAccessTokenConverter(this);
	}

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
		OAuth2Authentication auth = super.extractAuthentication(map);
		AccessTokenMapper details = new AccessTokenMapper();
		if (map.get("user_name") != null) {
			details.setUsername((String) map.get("user_name"));
		}
		if (auth.getAuthorities() != null && auth.getAuthorities().size() > 0) {
			List<String> roles = new ArrayList<>();
			auth.getAuthorities().forEach(role -> {
				roles.add(role.getAuthority());
			});
		}
		auth.setDetails(details);
		return auth;
	}

}
