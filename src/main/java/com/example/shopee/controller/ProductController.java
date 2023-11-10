package com.example.shopee.controller;

import com.example.shopee.dto.error.MessageErrorDTO;
import com.example.shopee.dto.product.ProductCreateDTO;
import com.example.shopee.dto.product.ProductSearchDTO;
import com.example.shopee.dto.product.ProductUpdateDTO;
import com.example.shopee.entity.Product;
import com.example.shopee.mapper.IProductMapper;
import com.example.shopee.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin("http://127.0.0.1:5500/")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    IProductMapper productMapper;

    @GetMapping("")
    public ResponseEntity<Page<Product>> getProducts(ProductSearchDTO productSearchDTO,
                                                 @PageableDefault(size = 4, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> productList = productService.findAll(productSearchDTO, pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK) ;
    }

    @GetMapping("{id}")
    public  ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@Validated @RequestBody ProductCreateDTO productCreateDTO, BindingResult bindingResult) {
        new ProductCreateDTO().validate(productCreateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            List<MessageErrorDTO> messageErrorDTOS = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                MessageErrorDTO messageErrorDTO = new MessageErrorDTO(fieldError.getField(), fieldError.getDefaultMessage());
                messageErrorDTOS.add(messageErrorDTO);
            }
            return new ResponseEntity<>(messageErrorDTOS, HttpStatus.BAD_REQUEST);
        }
        productService.create(productMapper.toProductFromProductCreateDTO(productCreateDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity<>(product ,HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public  ResponseEntity<?> update(@PathVariable("id") int id,
                                     @Validated @RequestBody ProductUpdateDTO productUpdateDTO, BindingResult bindingResult) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        new ProductUpdateDTO().validate(productUpdateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            List<MessageErrorDTO> messageErrorDTOList = new ArrayList<>();

            for (FieldError fieldError: bindingResult.getFieldErrors()) {
                MessageErrorDTO messageErrorDTO = new MessageErrorDTO(fieldError.getField(), fieldError.getDefaultMessage());
                messageErrorDTOList.add(messageErrorDTO);
            }
            return new ResponseEntity<>(messageErrorDTOList, HttpStatus.BAD_REQUEST.BAD_REQUEST);
        }

        Product productNew = productMapper.toProductFromProductUpdateDTO(productUpdateDTO);
        productNew.setId(id);
        productService.update(productNew);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
