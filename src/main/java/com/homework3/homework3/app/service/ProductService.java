package com.homework3.homework3.app.service;


import com.homework3.homework3.app.dto.ProductDto;
import com.homework3.homework3.app.dto.ProductResponseDto;
import com.homework3.homework3.app.dto.ProductSaveRequestDto;
import com.homework3.homework3.app.dto.ProductUpdateDto;
import com.homework3.homework3.app.service.entityService.ProductEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductEntityService productEntityService;


    public ProductResponseDto findById(Long id) {
        return productEntityService.findById(id);
    }

    public ProductResponseDto save(ProductSaveRequestDto productSaveRequestDto){

        return productEntityService.save(productSaveRequestDto);
    }

    public void deleteProduct(Long id){

        productEntityService.delete(id);
    }

   public ProductResponseDto updateProductPrice(Long id, ProductUpdateDto productUpdateDto){
       return productEntityService.updateProductPrice(id, productUpdateDto);
   }

    public List<ProductDto> findAll() {

        return productEntityService.findAll();

    }
}
