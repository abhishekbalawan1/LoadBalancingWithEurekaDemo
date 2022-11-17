package com.example.Address;

import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AddressController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private RestTemplate template;

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @GetMapping("/address")
    public AddressComponent getAddress(){
        logger.info("Address service through port : " + port);

        String message = template.getForObject("http://localhost:8087/hello", String.class);

        return new AddressComponent("Belgaum", "Location1", 1);
    }
}
