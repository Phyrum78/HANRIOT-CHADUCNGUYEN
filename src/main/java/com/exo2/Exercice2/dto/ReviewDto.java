package com.exo2.Exercice2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto extends AbsDto<Long> {
    @Schema(accessMode = Schema.AccessMode.AUTO)
    private Long id;

    private Long userId;
    private Long reviewerId;
    private int rating;
    private String comment;
}
