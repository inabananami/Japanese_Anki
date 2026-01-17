package com.inabananami.japanesedemo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;

    private String email;

    @Pattern(regexp = "^\\d{11}$", message = "手机号必须是11位数字")
    private String phoneNumber;

    @Pattern(regexp = "^(男|女|其他)$", message = "性别必须为“男”、“女”、“其他”")
    private String gender;

    private String avatar;

    @Pattern(regexp = "^\\S{1,11}$", message = "昵称长度应为1-11位")
    private String nickname;

    private LocalDate birthday;

    @Pattern(regexp = "^(N1|N2|N3|N4|N5)$", message = "语言等级必须是“N1-N5”之一")
    private String languageLevel;

    @Min(value = 5, message = "每日目标必须介于5-150个单词之间")
    @Max(value = 150, message = "每日目标必须介于5-150个单词之间")
    private Integer dailyTarget;

    @Min(value = 0, message = "词汇量不能小于零")
    @Max(value = 50000, message = "词汇量不能大于50000")
    private Integer knownWordCount;
}
