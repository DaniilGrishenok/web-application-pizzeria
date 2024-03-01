package ru.grishenokdaniil.webapplicationpizzeria.model.enams;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
        USER,
        ADMIN,
        MANAGER;

        @Override
        public String getAuthority() {
                return name();
        }
}
