package com.lcwd.electronic.store.ElectronicStores.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String categoryId;

    @NotBlank
    @Min(value = 4, message = "Title must be minimum value of 4 characters")
    private String title;

    @NotBlank(message = "Description is required !!")
    private String description;

    @NotBlank(message = "Cover Image is required !!")
    private String coverImage;

}
