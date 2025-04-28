package com.lcwd.electronic.store.ElectronicStores.services;

import com.lcwd.electronic.store.ElectronicStores.dtos.PageableResponse;
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

    //get all user
   PageableResponse<UserDto> getAlUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single user by Id
    UserDto getUserById(String userId);

    //gert Single user by email
    UserDto getUserByEmail(String email);

    //serch user
    List<UserDto> searchUser(String keyword);
}
