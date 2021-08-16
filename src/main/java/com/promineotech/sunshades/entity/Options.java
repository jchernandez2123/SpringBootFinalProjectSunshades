/**
 * 
 */
package com.promineotech.sunshades.entity;

/**
 * @author 12086
 *
 */
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Options {
  private Long optionPK;
  private String optionId;
  private PartType parts;
  private String manufacturer;
  private String name;
  private BigDecimal price;
}