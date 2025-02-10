package com.example.Ztek_Project.Controller;

import com.example.Ztek_Project.Entity.CustomerData;
import com.example.Ztek_Project.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public Map<String,Object> getRewards(@PathVariable Long customerId){
        return customerService.calculateRewards(customerId);
    }


    @PostMapping("data")
    public CustomerData addCutomerData(@RequestBody CustomerData customerData){
        return customerService.saveCustomer(customerData);
    }


}
