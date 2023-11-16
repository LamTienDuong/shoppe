package com.example.shopee.mapper;

import com.example.shopee.dto.product.ProductCreateDTO;
import com.example.shopee.dto.product.ProductUpdateDTO;
import com.example.shopee.entity.Product;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component("productMapper")
public interface IProductMapper {
    Product toProductFromProductCreateDTO(ProductCreateDTO productCreateDTO);
    Product toProductFromProductUpdateDTO(ProductUpdateDTO productUpdateDTO);

}
