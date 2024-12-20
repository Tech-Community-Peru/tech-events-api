package com.techcommunityperu.techcommunityperu.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "password_reset")
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 32, max = 64)
    @Column(name = "random", nullable = false, unique = true)
    private String token;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private Usuario usuario;

    public void setExpirationDate(Integer minutes) {
        this.expirationDate = LocalDateTime.now().plusMinutes(minutes);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }
}
