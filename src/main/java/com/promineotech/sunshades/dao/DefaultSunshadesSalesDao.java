/**
 * 
 */
package com.promineotech.sunshades.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.sunshades.entity.Sunshades;
import com.promineotech.sunshades.entity.SunshadesProducts;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
/**
 * @author 12086
 *
 */
public class DefaultSunshadesSalesDao implements SunshadesSalesDao{

  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Sunshades> fetchSunshades(SunshadesProducts products, String styleType) {
    log.debug("DAO: products={}, styleType={}", products, styleType);
    
    //@formatter:off
    String sql = ""
        +"SELECT * "
        + "FROM products "
        +"WHERE product_id = :product_id AND style_type = :style_type";
    
    //@formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("product_id", products.toString());
    params.put("style_type", styleType); // not sure about this **********************************
    
    return jdbcTemplate.query(sql, params,
        new RowMapper<Sunshades>() {

      @Override
      public Sunshades mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Sunshades.builder()
            .price(new BigDecimal(rs.getString("price")))
            .ProductId(SunshadesProducts.valueOf(rs.getString("product_id")))// this is how you load an enum model it has to be exactly the same as this
            .productPK(rs.getLong("product_pk"))
            .StyleType(rs.getString("style_type"))
            .build();
        // @formatter:on
      }});
  }}
