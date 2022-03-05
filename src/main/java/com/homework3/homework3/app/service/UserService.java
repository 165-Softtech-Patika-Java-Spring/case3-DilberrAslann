package com.homework3.homework3.app.service;

import com.homework3.homework3.app.dto.*;
import com.homework3.homework3.app.service.entityService.UserEntityService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityService userEntityService;

    public UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        return userEntityService.updateUser(id, userUpdateDto);
    }


    public List<UserDto> findAll() {
        return userEntityService.findAll();
    }

    public UserResponseDto save(UserSaveRequestDto userSaveRequestDto){

        return userEntityService.save(userSaveRequestDto);
    }

    public UserResponseDto findById(Long id) {
        return userEntityService.findById(id);
    }

    public UserResponseDto findByName(String name) {
        return userEntityService.findByName(name);
    }

    public void deleteUser(Long id, UserDeleteRequestDto userDeleteRequestDto) {

        userEntityService.deleteUser(id, userDeleteRequestDto);
    }

}
