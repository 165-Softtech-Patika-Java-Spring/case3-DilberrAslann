package com.homework3.homework3.app.dto;

import com.homework3.homework3.app.enums.UserType;
import lombok.Data;


@Data
public class UserDto {
    private String name;
    private String surname;
    private Long phoneNumber;
    private String email;
    private UserType userType;

}
