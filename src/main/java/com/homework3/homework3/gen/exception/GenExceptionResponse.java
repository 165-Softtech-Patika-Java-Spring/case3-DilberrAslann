package com.homework3.homework3.gen.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class GenExceptionResponse {
    private Date errorDate;
    private String message;
    private String detail;
}
