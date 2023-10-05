package com.sygal.spring.login.payload.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {
  private Long id;
  private String username;
  private String email;
  private final List<String> roles;
}
