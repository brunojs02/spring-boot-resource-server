package com.brunojs02.resourceserver.resources;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunojs02.resourceserver.config.AccessTokenMapper;

@RestController
@RequestMapping("teste")
public class TesteResource {

	@GetMapping
	public String index() {
		AccessTokenMapper access = (AccessTokenMapper) ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext()
				.getAuthentication().getDetails()).getDecodedDetails();
		System.out.println(access.getUsername());
		return "It's work...";
	}

}