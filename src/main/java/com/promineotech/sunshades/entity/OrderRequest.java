/**
 * 
 */
package com.promineotech.sunshades.entity;

/**
 * @author 12086
 *
 */

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Data;



@Data
public class OrderRequest {
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String color;
  
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String customer;
  

  @NotNull
  private SunshadesProducts model;
  

  
  
  
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String style_type;

  @Positive
  @Min(2)
  @Max(2)
  private int temples;
  private List<@NotNull @Length(max = 30)  @Pattern(regexp = "[A-Z0-9_]*") String> options;
  
}