package com.lcwd.electronic.store.ElectronicStores.services;

import com.lcwd.electronic.store.ElectronicStores.dtos.UserDto;
import com.lcwd.electronic.store.ElectronicStores.entities.User;

import java.util.List;

public interface UserService {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userId);

    //delete
    void deleteUser(String userId);

    List<UserDto> getAlUser();

    //get single user by Id
    UserDto getUserById(String userId);

    //gert Single user by email
    UserDto getUserByEmail(String email);

    //serch user
    List<UserDto> searchUser(String keyword);
}
