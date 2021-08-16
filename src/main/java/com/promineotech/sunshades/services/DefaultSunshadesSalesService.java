/**
 * 
 */
package com.promineotech.sunshades.services;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.sunshades.dao.SunshadesSalesDao;
import com.promineotech.sunshades.entity.Sunshades;
import com.promineotech.sunshades.entity.SunshadesProducts;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 12086
 *
 */
@Service // this tells spring that it needs to manage the class and that is a candidate for injection
@Slf4j 
public class DefaultSunshadesSalesService implements SunshadesSalesService{
  
  
  
  @Autowired
  private SunshadesSalesDao sunshadesSalesDao; 
  private List<Sunshades> Sunshades = null;
  
  @Transactional(readOnly= true)
  @Override
  public List<Sunshades> fetchSunshades (SunshadesProducts product, String options) {
    log.info("The fetchSunshades method was called with product={} and options={}",
        product ,options);
    
    List<Sunshades> sunshades = sunshadesSalesDao.fetchSunshades(product,options);
    
    if(sunshades.isEmpty()) {
      String msg = String.format("No sunshades found with product=%s and options=%s",
          product,options);
      
      
      throw new NoSuchElementException(msg);
    }
    
    Collections.sort(sunshades);
    
    return Sunshades;
  }

}
