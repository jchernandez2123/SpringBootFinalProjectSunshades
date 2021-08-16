/**
 * 
 */
package com.promineotech.sunshades.entity;

/**
 * @author 12086
 *
 */

import java.math.BigDecimal;
import java.util.Comparator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.sunshades.entity.Sunshades;
import com.promineotech.sunshades.entity.SunshadesProducts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sunshades  implements Comparable<Sunshades> {
  
private long productPK;
private SunshadesProducts ProductId;
private String StyleType;
private BigDecimal price;


@JsonIgnore
public Long getProductsPK() {
  return productPK;
}


@Override
public int compareTo(Sunshades that) {
  
  //@formatter:off
return Comparator
  .comparing(Sunshades::getProductId)
  .thenComparing(Sunshades::getStyleType)
  .compare(this, that);
  //@formatter:on
 
}
}


