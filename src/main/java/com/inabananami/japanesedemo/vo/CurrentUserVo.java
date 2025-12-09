package com.inabananami.japanesedemo.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserVo {
    private String phoneNumber;
    private String email;
    private String gender;
    private String avatar;
    private String nickname;
    private LocalDate birthday;
    private String languageLevel;
    private Integer dailyTarget;
    private Integer knownWordCount;
}