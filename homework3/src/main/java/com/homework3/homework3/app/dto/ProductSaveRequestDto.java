package com.homework3.homework3.app.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductSaveRequestDto {

    private String name;
    private BigDecimal price;


}
