package com.exo2.Exercice2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyDto extends AbsDto<Long> {
    private Long id;
}
