package com.lcwd.electronic.store.ElectronicStores.controllers;

import com.lcwd.electronic.store.ElectronicStores.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.ElectronicStores.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStores.dtos.UserDto;
import com.lcwd.electronic.store.ElectronicStores.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDto1 = userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") String userId,
            @Valid @RequestBody UserDto userDto
    ){
        UserDto updatedUerDto = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUerDto, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(
            @PathVariable("userId") String userId
    ){
        userService.deleteUser(userId);
       ApiResponseMessage message = ApiResponseMessage.builder().message("User Deleted successfully").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //get All
    @GetMapping
    public  ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc",required = false)String sortDir

    ){
        return new ResponseEntity<>(userService.getAlUser(pageNumber,pageSize,sortBy,sortDir), HttpStatus.OK);
    }

    //get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    //get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    //serach user
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords){
        return new ResponseEntity<>(userService.searchUser(keywords), HttpStatus.OK);
    }

}
