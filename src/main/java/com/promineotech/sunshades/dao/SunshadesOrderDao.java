/**
 * 
 */
package com.promineotech.sunshades.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import com.promineotech.sunshades.entity.Color;
import com.promineotech.sunshades.entity.Customer;
import com.promineotech.sunshades.entity.Sunshades;
import com.promineotech.sunshades.entity.SunshadesProducts;
import com.promineotech.sunshades.entity.Options;
import com.promineotech.sunshades.entity.Order;
import com.promineotech.sunshades.entity.OrderRequest;
/**
 * @author 12086
 *
 */
public interface SunshadesOrderDao {

  /**
   * @param customer
   * @return
   */
  Optional <Customer> fetchCustomer(String customerId);
Optional <Sunshades> fetchProducts(SunshadesProducts products, String styleType);
Optional <Color> fetchColor(String colorId);

/**
 * @param customer
 * @param sunshades
 * @param color
 * @param price
 * @param options 
 * @return
 */
Order saveOrder(Customer customer, Sunshades sunshades, Color color,
    BigDecimal price, List<Options> options);
/**
 * @param options
 * @return
 */
List<Options> fetchOptions( List<String> optionsIds);




}
