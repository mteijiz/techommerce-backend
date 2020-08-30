package com.techommerce.backend.service;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

public interface KeycloakService {

	public KeycloakPrincipal<KeycloakSecurityContext> getJwtToken();
	
}
