package com.demo.test.model.request;

import com.college.etut.entity.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank
    @Size(max = 100)
    private String username;
    @NotBlank
    @Size(max = 50)
    private String password;
    @NotNull
    private RoleType roleType;
    @Email
    private String email;

}