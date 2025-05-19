package com.lcwd.electronic.store.ElectronicStores.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateOrderRequest {

    @NotBlank(message = "Cart id is required !!")
    private String cartId;
    @NotBlank(message = "Cart id is required !!")
    private String userId;
    private String orderStatus;
    private String paymentStatus;
    @NotBlank(message = "Address is required !!")
    private String billingAddress;
    @NotBlank(message = "Phone Number is required !!")
    private String billingPhone;
    @NotBlank(message = "Billing name is required !!")
    private String billingName;


}
