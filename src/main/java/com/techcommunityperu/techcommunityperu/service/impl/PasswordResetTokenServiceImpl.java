package com.techcommunityperu.techcommunityperu.service.impl;

import com.techcommunityperu.techcommunityperu.integration.email.service.EmailService;
import com.techcommunityperu.techcommunityperu.model.entity.PasswordResetToken;
import com.techcommunityperu.techcommunityperu.model.entity.Usuario;
import com.techcommunityperu.techcommunityperu.repository.PasswordResetTokenRepository;
import com.techcommunityperu.techcommunityperu.repository.UserRepository;
import com.techcommunityperu.techcommunityperu.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Value("${spring.mail.username")
    private String mailFrom;
    @Override
    public void createAndSendPasswordResetToken(String email) throws Exception {

    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return null;
    }

    @Override
    public void removeResetToken(PasswordResetToken passwordResetToken) {

    }

    @Override
    public boolean isValidToken(String token) {
        return false;
    }

    @Override
    public void resetPassword(String token, String newPassword) {

    }
}
