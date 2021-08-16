package com.promineotech.sunshades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

  }

}
