package com.homework3.homework3.app.controller;

import com.homework3.homework3.app.dto.*;
import com.homework3.homework3.gen.dto.RestResponse;
import com.homework3.homework3.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

   //Ürün idsinden ürünü bulma
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        ProductResponseDto productResponseDto = productService.findById(id);

        return ResponseEntity.ok(RestResponse.of(productResponseDto));
    }


    //Ürün kaydetme
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody ProductSaveRequestDto productSaveRequestDto){

        ProductResponseDto productResponseDto = productService.save(productSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(productResponseDto));
    }


    //Ürün silme
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

         productService.deleteProduct(id);

        return ResponseEntity.ok(RestResponse.empty());
    }




    //Ürün fiyatı güncelleme
   @PatchMapping("/updateProductPrice/{id}")
   public ResponseEntity updateProductPrice(@PathVariable("id") Long id, @RequestBody ProductUpdateDto productUpdateDto){

       ProductResponseDto productResponseDto = productService.updateProductPrice(id, productUpdateDto);

       return ResponseEntity.ok(RestResponse.of(productResponseDto));
   }


    //Ürünleri listeleme
    @GetMapping("/findAll")
    public ResponseEntity findAll(){

        List<ProductDto> productDtoList = productService.findAll();

        return ResponseEntity.ok(RestResponse.of(productDtoList));
    }

}
