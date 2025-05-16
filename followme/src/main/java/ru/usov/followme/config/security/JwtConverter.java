package ru.usov.followme.config.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

public interface JwtConverter extends Converter<Jwt, Collection<GrantedAuthority>> {
}
