/**
 * 
 */
package com.promineotech.sunshades.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.sunshades.entity.Color;
import com.promineotech.sunshades.entity.Customer;
import com.promineotech.sunshades.entity.Sunshades;
import com.promineotech.sunshades.entity.SunshadesProducts;
import com.promineotech.sunshades.entity.Options;
import com.promineotech.sunshades.entity.PartType;
import com.promineotech.sunshades.entity.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 12086
 *
 */

  @Component
  @Slf4j
  public class DefaultSunshadesOrderDao implements SunshadesOrderDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    public Order saveOrder(Customer customer, Sunshades sunshades, Color color,
        BigDecimal price, List<Options> options) {
      SqlParams params =
          generateInsertSql(customer, sunshades, color, price);
     
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(params.sql, params.source, keyHolder);
   
      Long orderPK = keyHolder.getKey().longValue();
      
      saveOptions(options, orderPK);
      //@formatter:off
      return Order.builder()
          .orderPK(orderPK)
          .customer(customer)
          .products(sunshades)
          .color(color)
          .options(options)
          .price(price)
          .build();
      //@formatter:on
    }
    /**
     * @param options
     * @param orderPK
     */
    private void saveOptions(List<Options> options, Long orderPK) {
      for(Options option : options) {
        SqlParams params = generateInsertSql(option, orderPK);
        jdbcTemplate.update(params.sql, params.source);
      }
      
    }
    /**
     * @param option
     * @param orderPK
     * @return
     */
    private SqlParams generateInsertSql(Options option, Long orderPK) {
      SqlParams params = new SqlParams();
      // @formatter:off
      params.sql = "INSERT INTO order_options ("
          + "option_fk, order_fk"
          + ") VALUES ("
          + ":option_fk, :order_fk"
          + ")";
      // @formatter:on
      params.source.addValue("option_fk", option.getOptionPK());
      params.source.addValue("order_fk", orderPK);
      
      return params;
    }
    /**
     * @param customer
     * @param sunshades
     * @param color
     * @param price
     * @return
     */
    private SqlParams generateInsertSql(Customer customer, Sunshades products, Color color,
        BigDecimal price) {
      //@formatter:off
      String sql = ""
          + "INSERT INTO orders ("
          + "customer_fk, color_fk, product_fk, price"
          + ") VALUES ("
          + ":customer_fk, :color_fk, :product_fk, :price"
          + ")";
      //@formatter:on
      
      SqlParams params = new SqlParams();
      params.sql = sql;
      params.source.addValue("customer_fk", customer.getCustomerPK());
      params.source.addValue("color_fk", color.getColorPK());
      params.source.addValue("product_fk", products.getProductsPK());
      params.source.addValue("price", price);
      
      return params;
    }
    public List<Options>fetchOptions (List<String> optionIds){
      if(optionIds.isEmpty()) {
        return new LinkedList<>();
        
      }
      Map<String , Object>  params = new HashMap<>();
      
      //@formatter:off
      String sql = ""
          + "SELECT * "
          + "FROM options "
          + "WHERE option_id IN (";
      //@formatter:on
      
      for (int index = 0; index< optionIds.size(); index++) {
        String key = "option_" + index;
        sql += ":" + key + ", ";
        params.put(key, optionIds.get(index));
      }
      
      sql = sql.substring(0, sql.length() - 2);
      sql += ")";
      return jdbcTemplate.query(sql, params, new RowMapper<Options>() {

        @Override
        public Options mapRow(ResultSet rs, int rowNum) throws SQLException {
          //@formatter:off
          return Options.builder()
              .parts(PartType.valueOf(rs.getString("parts")))
              .name(rs.getString("name"))
              .optionId(rs.getString("option_id"))
              .optionPK(rs.getLong("option_pk"))
              .price(rs.getBigDecimal("price"))
              .build();
          //@formatter:on
        }});
      
    }
    public Optional <Customer> fetchCustomer(String customerId) {
    //@formatter:off
    
    
    String sql = ""
        + "SELECT * "
        + "FROM customers "
        + "WHERE customer_id = :customer_id";
     //@formatter:on
        
      Map<String,Object> params = new HashMap<>();
    params.put("customer_id", customerId);
      
      return Optional.ofNullable(
          jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
      
    }
  /**
   * 
   * @author 12086
   *
   */
    public Optional <Sunshades> fetchSunshadesProducts(SunshadesProducts products) {
      //@formatter:off
      String sql = ""
          + "SELECT * "
          + "FROM products "
          + "WHERE product_id = :product_id ";
      //@formatter:on
      
      Map<String, Object> params = new HashMap<>();
      params.put("product_id", products.toString());

      
      
      return Optional.ofNullable(
          jdbcTemplate.query(sql, params, new ProductResultSetExtractor()));
    }
    
    /**
     * 
     * @author 12086
     *
     */
    public Optional <Color> fetchColor(String colorId) {
      //@formatter:off
      String sql = ""
          + "SELECT * "
          + "FROM colors "
          + "WHERE color_id = :color_id";
      //formatter:on
      Map<String, Object> params = new HashMap<>();
      params.put("color_id", colorId);
      
      return Optional.ofNullable(
          jdbcTemplate.query(sql, params, new ColorResultSetExtractor()));
    }
    @Override
    public Optional<Sunshades> fetchProducts(SunshadesProducts products, String styleType) {
      // TODO Auto-generated method stub
      return null;
    }

    
    }
    
    /**
     * 
     * @author 12086
     *
     *
     *
     */
    

  class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {// NOT SURE 

    @Override
    public Customer extractData(ResultSet rs) 
        throws SQLException, DataAccessException {
       rs.next();
       
       //@formatter:off
       return Customer.builder()
           .customerId(rs.getString("customer_id"))
           .customerPK(rs.getLong("customer_pk"))
           .firstName(rs.getString("first_name"))
           .lastName(rs.getString("last_name"))
           .phone(rs.getString("phone"))
           .build();
       //@formatter:on
    }
    
  }
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }

  class ProductResultSetExtractor implements ResultSetExtractor<Sunshades>{
    @Override
    public Sunshades extractData(ResultSet rs) throws SQLException{
      rs.next();
      
      //@formatter:off
      return Sunshades.builder()
          .productPK(rs.getLong("product_pk"))
          .ProductId(SunshadesProducts.valueOf(rs.getString("product_id")))
          .StyleType(rs.getString("style_type"))
          .price(rs.getBigDecimal("price"))
          
          .build();
      //@formatter:on
         
    }
  }
  class ColorResultSetExtractor implements ResultSetExtractor<Color>{
    @Override
    public Color extractData(ResultSet rs) throws SQLException{
      rs.next();
      
      //@formatter:off
      return Color.builder()
          .colorPK(rs.getLong("color_pk"))
          .colorId(rs.getString("color_id"))
          .color(rs.getString("color"))
          .price(rs.getBigDecimal("price"))
          .build();
      //@formatter:on
         
    }
  

  }

