package com.project.SmartLeave.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "leave_balance")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Integer casualBalance;

    private Integer sickBalance;

    private Integer earnedBalance;

    // getters setters
}