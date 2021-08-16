/**
 * 
 */
package com.promineotech.sunshades.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.sunshades.Constants;
import com.promineotech.sunshades.entity.Sunshades;
import com.promineotech.sunshades.entity.SunshadesProducts;



import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;



import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * @author 12086
 *
 */

@RestController
@Slf4j
@Validated
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Sunshades Order Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public class SunshadesSalesController {
  SunshadesSalesController sunshadesSalesService;

 // public class SunshadesOrderController {

    @Operation(
            summary = "Returns a list of Sunshades",
            description = "Returns a list of Sunshades given an optional model and/or trim",
            responses = {
              @ApiResponse(
                      responseCode = "200", 
                      description = "A list of Sunshades is returned", 
                      content = @Content(
                              mediaType = "application/json", 
                              schema = @Schema(
                                      implementation = Sunshades.class))),
              @ApiResponse(
                      responseCode = "400", 
                      description = "The request parameters are invalid", 
                      content = @Content(
                              mediaType = "application/json")),
              @ApiResponse(
                      responseCode = "404", 
                      description = "No Sunshades were found with the input criteria", 
                      content = @Content(
                              mediaType = "application/json")),
              @ApiResponse(
                      responseCode = "500", 
                      description = "An unplanned error occurred.", 
                      content = @Content(
                              mediaType = "application/json"))
            },
            parameters = {
              @Parameter(
                      name = "product", 
                      allowEmptyValue = false, 
                      required = false, 
                      description = "The product name (i.e., 'OAKLEY')"),
              @Parameter(
                      name = "style_type", 
                      allowEmptyValue = false, 
                      required = false, 
                      description = "The style type (i.e., 'Sport')")
            }
    ) 
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<Sunshades> fetchSunshades(
        @RequestParam (required =false)
          SunshadesProducts products, 
        @Length(max = Constants.STYLE_TYPE_MAX_LENGTH)  
        @Pattern(regexp = "[\\w\\s]*")
        @RequestParam (required = false)
        String style_type) {
      return sunshadesSalesService.fetchSunshades(products,style_type);
    }
  }
    
  


