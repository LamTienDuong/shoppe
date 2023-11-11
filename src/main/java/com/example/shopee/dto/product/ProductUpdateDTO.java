package com.example.shopee.dto.product;


import com.example.shopee.dto.category.CategoryDTO;
import com.example.shopee.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductUpdateDTO implements Validator {
    @NotBlank(message = "Tên không đươc để trống")
    private String title;

    @NotBlank(message = "Giá nhập không đươc để trống")
    private String price;

    @NotBlank(message = "Giá bán không đươc để trống")
    private String discount;

    @NotBlank(message = "Số lượng không đươc để trống")
    private String quantity;


    @NotBlank(message = "Tên không đươc để trống")
    private String image;

    @NotBlank(message = "Tên không đươc để trống")
    private String description;

    @Valid
    private CategoryDTO category;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductUpdateDTO productUpdateDTO = (ProductUpdateDTO) target;

        // Số lượng.
        if (!productUpdateDTO.getQuantity().trim().equals("")) {
            int quantity;
            try {
                quantity = Integer.parseInt(productUpdateDTO.getQuantity());
                if (quantity <= 0) {
                    errors.rejectValue("quantity", "", "Số lượng phải là số dương");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("quantity", "", "Số lượng không đúng định dạng");
            }
        }

        // Giá bán.
        if (!productUpdateDTO.getDiscount().trim().equals("")) {
            double discount;
            try {
                discount = Double.parseDouble(productUpdateDTO.getDiscount());
                if (discount <= 0) {
                    errors.rejectValue("discount", "", "Giá bán phải là số dương.");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("discount", "", "Giá bán không đúng định dạng.");
            }
        }

        // Giá nhập.
        if (!productUpdateDTO.getPrice().trim().equals("")) {
            double price;
            try {
                price = Double.parseDouble(productUpdateDTO.getPrice());
                if (price <= 0) {
                    errors.rejectValue("price", "", "Giá nhập phải là số dương.");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("price", "", "Giá nhập không đúng định dạng.");
            }

        }
    }
}
