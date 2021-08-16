/**
 * 
 */
package com.promineotech.sunshades.services;

import java.util.List;
import com.promineotech.sunshades.entity.Sunshades;
import com.promineotech.sunshades.entity.SunshadesProducts;

/**
 * @author 12086
 *
 */
public interface SunshadesSalesService {
  

public List<Sunshades> fetchSunshades (SunshadesProducts products, String style_type);
}

