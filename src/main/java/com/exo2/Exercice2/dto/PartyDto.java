package com.exo2.Exercice2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyDto extends AbsDto<Long> {

    private Long id;

    private String name;
    private String description;
    private String location;
    private Timestamp date;
    private int maxParticipants;
    private boolean isPaid;
    UserDto userOwner;
    private List<UserDto> participants;
}
