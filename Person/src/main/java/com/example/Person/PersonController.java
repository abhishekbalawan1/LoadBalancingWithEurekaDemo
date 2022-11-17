package com.example.Person;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@RibbonClient(name="person-service", configuration = RibbonConfiguration.class)
public class PersonController {

    @Autowired
    private RestTemplate template;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @GetMapping("/person")
    public PersonComponent getPerson(){
        return new PersonComponent("Raju", "Pune", 32);
    }

    @GetMapping("/address")
    public AddressComponent getAddress(){
        ServiceInstance serviceInstance=loadBalancer.choose("ADDRESS-SERVICE");

        System.out.println(serviceInstance.getUri());

        String url = serviceInstance.getUri().toString()+"/address";
        System.out.println("url accessed : "+url);
        //String url = "http://person-service/address";
        AddressComponent address = template.getForObject(url, AddressComponent.class);
        return address;
        //return new AddressComponent("city", "street", 1);
    }
}
