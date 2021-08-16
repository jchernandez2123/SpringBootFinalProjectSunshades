/**
 * 
 */
package com.promineotech.sunshades.dao;
import java.util.List;
import com.promineotech.sunshades.entity.Sunshades;
import com.promineotech.sunshades.entity.SunshadesProducts;

/**
 * @author 12086
 *
 */
public interface SunshadesSalesDao {
  /**
   * @param products
   * @param styleType
   * @return
   */
  List<Sunshades> fetchSunshades(SunshadesProducts products, String styleType);/// im not sure it the styleType goes here
}
