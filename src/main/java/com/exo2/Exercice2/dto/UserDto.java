package com.exo2.Exercice2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends AbsDto<Long> {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    private String email;

    private String password;
    private String city;
    private String region;
    private int age;
    private String interests;
}
