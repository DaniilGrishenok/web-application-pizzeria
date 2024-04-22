    package ru.grishenokdaniil.webapplicationpizzeria.model.entitys;

    import javax.persistence.*;
    import lombok.Data;
    import lombok.Getter;
    import lombok.Setter;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import ru.grishenokdaniil.webapplicationpizzeria.model.enams.Role;

    import java.time.LocalDateTime;
    import java.util.Collection;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Set;

    @Entity
    @Table(name = "users")
    @Data
    public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;

        @Column(unique = true)
        private String email;

        @Column(unique = true)
        private String numberPhone;
        @Column(nullable = true)
        private String name;
        @Column(nullable = true)
        private String surname;
        @Column(nullable = true)
        private String patronymic;

        private boolean active;

        @Column(length = 1000)
        private String password;

        @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
        @CollectionTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user_id"))
        @Enumerated(EnumType.STRING)
        private Set<Role> roles = new HashSet<>();
        @Column(nullable = true)
        private Long bonusBalance;
        @Column(nullable = true)
        private LocalDateTime dateOfCreated;

        private boolean isVerifiedMail;

        @PrePersist
        private void init() {
            dateOfCreated = LocalDateTime.now();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return roles;
        }

        @Override
        public String getUsername() {
            return email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return active;
        }
    }