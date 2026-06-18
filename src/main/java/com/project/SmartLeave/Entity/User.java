package com.project.SmartLeave.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Setter
    @Column(unique = true)
    @Email
    private String email;
    @Setter
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL)
    private LeaveBalance leaveBalance;

    public User() {
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
