package com.homework3.homework3.app.service.entityService;

import com.homework3.homework3.app.converter.AllMapper;
import com.homework3.homework3.app.dao.ProductDao;
import com.homework3.homework3.app.dto.ProductDto;
import com.homework3.homework3.app.dto.ProductResponseDto;
import com.homework3.homework3.app.dto.ProductSaveRequestDto;
import com.homework3.homework3.app.dto.ProductUpdateDto;
import com.homework3.homework3.app.entity.Product;
import com.homework3.homework3.gen.entity.BaseAdditionalFields;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductEntityService {
    private final ProductDao productDao;

    public ProductResponseDto save(ProductSaveRequestDto productSaveRequestDto) {

        Product product = AllMapper.INSTANCE.convertToProduct(productSaveRequestDto);

        setAdditionalFields(product);//Bu satırı yok edersek, hangi tarihte oluşturduğu bilgisine ulaşamayız.

        product = productDao.save(product);

        ProductResponseDto productResponseDto = AllMapper.INSTANCE.convertToProductResponseDto(product);

        return productResponseDto;
    }

    private void setAdditionalFields(Product product) {
        BaseAdditionalFields baseAdditionalFields = product.getBaseAdditionalFields();


        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            product.setBaseAdditionalFields(baseAdditionalFields);
        }if (product.getId() == null){
            baseAdditionalFields.setCreateDate(new Date());
        }
        baseAdditionalFields.setUpdateDate(new Date());
    }



    public ProductResponseDto findById(Long id) {
        Product product = getById(id);

        ProductResponseDto productResponseDto = AllMapper.INSTANCE.convertToProductResponseDto(product);

        return productResponseDto;

    }

    private Product getById(Long id) {
        Optional<Product> productOptional = productDao.findById(id);

        Product product;
        if (productOptional.isPresent()) {
            product = productOptional.get();
        } else {
            throw new RuntimeException("Item not found!");
        }
        return product;


    }

    public void delete(Long id) {
        Product product = getById(id);

        productDao.delete(product);
    }


    public List<ProductDto> findAll() {

        List<Product> productList = productDao.findAll();


        List<ProductDto> productDtoList= AllMapper.INSTANCE.convertToProductDtoList(productList);

        return productDtoList;
    }

    public ProductResponseDto updateProductPrice(Long id, ProductUpdateDto productUpdateDto) {
        Optional<Product> productOptional = productDao.findById(id);

        Product product;
        if (productOptional.isPresent()){
            product = productOptional.get();
        } else {
            throw new NotFoundException("Item not found!");
        }


        product.getBaseAdditionalFields().setUpdateDate(new Date());
        product.setPrice(productUpdateDto.getPrice());
        product= productDao.save(product);

        ProductResponseDto productResponseDto = AllMapper.INSTANCE.convertToProductResponseDto(product);

        return productResponseDto;
    }




}


