package ru.usov.followme.enums;


import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    AUTHORIZED_USER;

    private static final Set<String> ALL_ROLES = EnumSet.allOf(Role.class)
        .stream()
        .map(role -> role.name().toLowerCase())
        .collect(Collectors.toUnmodifiableSet());


    public static Set<String> getAllRoles() {
        return ALL_ROLES;
    }
}