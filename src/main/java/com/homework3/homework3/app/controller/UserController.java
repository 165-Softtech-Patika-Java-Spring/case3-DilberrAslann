package com.homework3.homework3.app.controller;

import com.homework3.homework3.app.dto.*;
import com.homework3.homework3.app.service.UserService;
import com.homework3.homework3.gen.dto.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    //Tüm kullanıcıları getirme
    @GetMapping("/findAll")
    public ResponseEntity findAll(){

        List<UserDto> userDtoList = userService.findAll();

        return ResponseEntity.ok(RestResponse.of(userDtoList));
    }


    //Kullanıcı kaydetme
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody UserSaveRequestDto userSaveRequestDto){

        UserResponseDto userResponseDto = userService.save(userSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(userResponseDto));

    }


    //Kullanıcı idsinden kullanıcıyı getirme
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        UserResponseDto userResponseDto = userService.findById(id);

        return ResponseEntity.ok(RestResponse.of(userResponseDto));

    }


    // Kullanıcı adından kullanıcıyı getirme
    @GetMapping("/findByName/{name}")
    public ResponseEntity findByName(@PathVariable String name){

        UserResponseDto userResponseDto= userService.findByName(name);

        return ResponseEntity.ok(RestResponse.of(userResponseDto));
    }



    //Kullanıcı bilgilerini güncelleme
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDto userUpdateDto){

        UserResponseDto userResponseDto = userService.updateUser(id, userUpdateDto);

        return ResponseEntity.ok(RestResponse.of(userResponseDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id, @RequestBody UserDeleteRequestDto userDeleteRequestDto){

       userService.deleteUser(id, userDeleteRequestDto);

        return ResponseEntity.ok(Void.TYPE);
    }



}
