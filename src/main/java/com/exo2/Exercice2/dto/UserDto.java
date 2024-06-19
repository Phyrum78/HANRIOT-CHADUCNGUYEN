package com.exo2.Exercice2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends AbsDto<Long> {
    private Long id;

    private String email;

    private String password;
    private String city;
    private String region;
    private int age;
    private String interests;
}
