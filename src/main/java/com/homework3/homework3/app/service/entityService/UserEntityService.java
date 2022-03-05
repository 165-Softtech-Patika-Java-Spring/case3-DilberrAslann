package com.homework3.homework3.app.service.entityService;

import com.homework3.homework3.app.converter.AllMapper;
import com.homework3.homework3.app.dao.UserDao;
import com.homework3.homework3.app.dto.*;
import com.homework3.homework3.app.entity.AppUser;
import com.homework3.homework3.gen.entity.BaseAdditionalFields;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityService {
    private final UserDao userDao;

    public List<UserDto> findAll() {

        List<AppUser> appUserList = userDao.findAll();

        List<UserDto> userDtoList = AllMapper.INSTANCE.convertToUserDtoList(appUserList);

        return userDtoList;
    }

    public UserResponseDto save(UserSaveRequestDto userSaveRequestDto) {

        AppUser user = AllMapper.INSTANCE.convertToUser(userSaveRequestDto);
        setAdditionalFields(user);//Bu satırı yok edersek, hangi tarihte oluşturduğu bilgisine ulaşamayız.


        user = userDao.save(user);

        UserResponseDto userResponseDto = AllMapper.INSTANCE.convertToUserResponseDto(user);

        return userResponseDto;
    }

    private void setAdditionalFields(AppUser user) {
        BaseAdditionalFields baseAdditionalFields = user.getBaseAdditionalFields();


        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
            user.setBaseAdditionalFields(baseAdditionalFields);
        }
        if (user.getId() == null) {
            baseAdditionalFields.setCreateDate(new Date());
        }
        baseAdditionalFields.setUpdateDate(new Date());
    }


    public UserResponseDto findById(Long id) {
        AppUser user = getById(id);

        UserResponseDto userResponseDto = AllMapper.INSTANCE.convertToUserResponseDto(user);

        return userResponseDto;
    }

    private AppUser getById(Long id) {
        Optional<AppUser> userOptional = userDao.findById(id);

        AppUser user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new RuntimeException("Item not found!");
        }
        return user;
    }

    public UserResponseDto findByName(String name) {
        AppUser user = getByName(name);

        UserResponseDto userResponseDto = AllMapper.INSTANCE.convertToUserResponseDto(user);

        return userResponseDto;
    }

    private AppUser getByName(String name) {
        Optional<AppUser> userOptional = userDao.findByName(name);

        AppUser user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new RuntimeException("Item not found!");
        }
        return user;
    }

    public UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        Optional<AppUser> userOptional = userDao.findById(id);

        AppUser user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new NotFoundException("Item not found!");
        }


        user.getBaseAdditionalFields().setUpdateDate(new Date());
        user.setName(userUpdateDto.getName());
        user = userDao.save(user);

        UserResponseDto userResponseDto = AllMapper.INSTANCE.convertToUserResponseDto(user);

        return userResponseDto;
    }


    public void deleteUser(Long id, UserDeleteRequestDto userDeleteRequestDto) {
        Optional<AppUser> userOptional = userDao.findById(id);

        AppUser user = userOptional.orElseThrow(() -> new RuntimeException("User Not Found!"));

        if (user.getPhoneNumber().equals(userDeleteRequestDto.getPhoneNumber()) && user.getName().equals(userDeleteRequestDto.getName())) {
            userDao.delete(user);
        } else {
            throw new RuntimeException(userDeleteRequestDto.getName() +
                    " user's phone does not match " + userDeleteRequestDto.getPhoneNumber());
        }

    }
}
