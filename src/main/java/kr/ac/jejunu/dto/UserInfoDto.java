package kr.ac.jejunu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
    private String email;
    private String password;
    private String name;
    private String auth;
}
