package com.daw135.dawFinalProyect.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthUtils {

    private AuthUtils() {
        // Clase utilitaria, no instanciable
    }

    /**
     * Obtiene el usuario autenticado actual
     * 
     * @return OidcUser o null si no hay autenticación
     */
    public static Optional<OidcUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof OidcUser) {
            return Optional.of((OidcUser) authentication.getPrincipal());
        }
        return Optional.empty();
    }

    /**
     * Obtiene el email del usuario actual
     * 
     * @return Email o null si no está autenticado
     */
    public static Optional<String> getCurrentUserEmail() {
        return getCurrentUser().map(OidcUser::getEmail);
    }

    /**
     * Obtiene los roles del usuario actual
     * 
     * @return Lista de roles (sin prefijo ROLE_), lista vacía si no está
     *         autenticado
     */
    public static List<String> getCurrentUserRoles() {
        return getCurrentUser()
                .map(user -> user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(role -> role.replace("ROLE_", ""))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    /**
     * Verifica si el usuario actual tiene un rol específico
     * 
     * @param role Rol a verificar (sin prefijo ROLE_)
     * @return true si tiene el rol, false si no está autenticado o no tiene el rol
     */
    public static boolean hasRole(String role) {
        return getCurrentUserRoles().contains(role.toUpperCase());
    }

    /**
     * Obtiene un claim específico del token del usuario
     * 
     * @param claimName Nombre del claim
     * @return Optional con el valor del claim o vacío si no existe
     */
    public static Optional<Object> getClaim(String claimName) {
        return getCurrentUser().map(user -> user.getClaim(claimName));
    }

    /**
     * Obtiene todos los claims del usuario
     * 
     * @return Map con los claims o mapa vacío si no está autenticado
     */
    public static Map<String, Object> getAllClaims() {
        return getCurrentUser()
                .map(OidcUser::getClaims)
                .orElse(Collections.emptyMap());
    }

    /**
     * Obtiene el ID del usuario en Auth0 (sub claim)
     * 
     * @return Optional con el ID de Auth0 o vacío si no está autenticado
     */
    public static Optional<String> getAuth0Id() {
        return getClaim("sub").map(Object::toString);
    }

    /**
     * Obtiene el nombre completo del usuario
     * 
     * @return Optional con el nombre completo o vacío si no está autenticado
     */
    public static Optional<String> getFullName() {
        return getClaim("name").map(Object::toString);
    }

    public static Optional<String> getEmail() {
        return getClaim("email").map(Object::toString);
    }

    /**
     * Verifica si hay un usuario autenticado
     * 
     * @return true si hay usuario autenticado, false en caso contrario
     */
    public static boolean isAuthenticated() {
        return getCurrentUser().isPresent();
    }
}