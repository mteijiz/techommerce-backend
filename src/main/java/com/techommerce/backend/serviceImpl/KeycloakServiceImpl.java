package com.techommerce.backend.serviceImpl;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techommerce.backend.service.KeycloakService;

@Service
public class KeycloakServiceImpl implements KeycloakService{

	@Override
	public KeycloakPrincipal<KeycloakSecurityContext> getJwtToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KeycloakPrincipal<KeycloakSecurityContext> keycloakJwtToken = null;
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof KeycloakPrincipal) 
				keycloakJwtToken = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
		}
		return keycloakJwtToken;
	}

}
