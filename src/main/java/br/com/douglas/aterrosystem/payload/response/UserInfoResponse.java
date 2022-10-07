package br.com.douglas.aterrosystem.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private String accessToken;
	private List<String> roles;
}
