package ru.usov.followme.config.security;

public class AuthorizationConstants {

    // authorization
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String BEARER = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
    public final static String CLIENT_REGISTRATION_ID = "default-keycloak-client";

    // claims
    public static final String REALM_ACCESS = "realm_access";
    public static final String ROLES = "roles";
    public static final String SUB = "sub";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
}
