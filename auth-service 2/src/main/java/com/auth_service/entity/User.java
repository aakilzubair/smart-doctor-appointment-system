package com.auth_service.entity;



import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 unique login field
    @Column(nullable = false, unique = true)
    private String email;

    // 🔐 hashed password
    @Column(nullable = false)
    private String password;

    // 🔥 roles (RBAC)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    // 🔥 account status
    @Column(nullable = false)
    private boolean enabled = true;

    // getters & setters

    public Long getId() { return id; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public Set<String> getRoles() { return roles; }

    public boolean isEnabled() { return enabled; }

    public void setId(Long id) { this.id = id; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setRoles(Set<String> roles) { this.roles = roles; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
