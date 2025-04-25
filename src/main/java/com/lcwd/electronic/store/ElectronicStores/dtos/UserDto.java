package com.lcwd.electronic.store.ElectronicStores.dtos;

import com.lcwd.electronic.store.ElectronicStores.validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @Size(min = 3, max = 30, message ="Invalid Name !!" )
    private String name;

    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message = "Invalid Email")
    @Email(message = "Invalid Email !!")
    @NotBlank(message = "Email is required !!")
    private String email;

    @NotBlank(message = "Password is required !1")
    private String password;

    @Size(min = 3, max = 12, message = "Gender is required !!")
    private String gender;

    @NotBlank(message = "Write something about yourself !!")
    private String about;

    @ImageNameValid
    private String imageName;
}
