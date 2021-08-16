/**
 * 
 */
package com.promineotech.sunshades.controllers;

/**
 * @author 12086
 *
 */

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.sunshades.Constants;
import com.promineotech.sunshades.entity.Sunshades;
import com.promineotech.sunshades.entity.SunshadesProducts;
import com.promineotech.sunshades.entity.Order;
import com.promineotech.sunshades.entity.OrderRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
@RestController
@Slf4j
@Validated
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Sunshades Order Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})


public class SunshadesOrderController {
  SunshadesOrderController sunshadesOrderService;

  @Operation(
          summary = "Create an order for a Sunshades",
          description = "Returns the created Sunshades",
          responses = {
            @ApiResponse(
                    responseCode = "201", 
                    description = "The created Sunshade is returned", 
                    content = @Content(
                            mediaType = "application/json", 
                            schema = @Schema(
                                    implementation = Order.class))),
            @ApiResponse(
                    responseCode = "400", 
                    description = "The request parameters are invalid", 
                    content = @Content(
                            mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "404", 
                    description = "A Sunshades component was not found with the input criteria", 
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
                    name = "orderRequest", 
                    required = true, 
                    description = "The order as JSON")
      
          }
  ) 
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Order createOrder(@Valid @RequestBody OrderRequest orderRequest) {
    return sunshadesOrderService.createOrder(orderRequest);
  }
   
}