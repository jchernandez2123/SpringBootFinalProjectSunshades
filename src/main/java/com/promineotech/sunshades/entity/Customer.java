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



@Data
@Builder
public class Customer {
  private Long customerPK;
  private String customerId;
  private String firstName;
  private String lastName;
  private String phone;
}