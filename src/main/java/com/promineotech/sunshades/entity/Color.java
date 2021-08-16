/**
 * 
 */
package com.promineotech.sunshades.entity;

/**
 * @author 12086
 *
 */
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
@Data
@Builder
public class Color {
 

    private Long colorPK;
    private String colorId;
    private String color;
    private BigDecimal price;
   
  }

