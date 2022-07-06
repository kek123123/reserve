package com.shnam.reserve.config.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ErrorResponseDto {
    private String code;
    private String message;
}
