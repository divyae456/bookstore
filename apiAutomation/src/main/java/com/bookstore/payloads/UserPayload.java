package com.bookstore.payloads;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class UserPayload {
    private String email;
    private String password;
} 