/**
 * 
 */
package com.promineotech.sunshades.entity;

/**
 * @author 12086
 *
 */
import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
  private Long orderPK;
  private Customer customer;
  private Sunshades products;
  private Color color;

  private List<Options> options;
  private BigDecimal price;
  
  @JsonIgnore
  public Long getOrderPK() {
    return orderPK;
  }
}