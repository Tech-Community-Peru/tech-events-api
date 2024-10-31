package com.techcommunityperu.techcommunityperu.integration.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    private String from;
    private String to;
    private String subject;
    private Map<String, Object> model;
}
