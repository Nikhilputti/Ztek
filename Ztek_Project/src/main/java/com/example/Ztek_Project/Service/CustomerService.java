package com.example.Ztek_Project.Service;


import com.example.Ztek_Project.Entity.CustomerData;
import com.example.Ztek_Project.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;



    public Map<String, Object> calculateRewards(Long customerId) {

        List<CustomerData> customerData = customerRepository.findByCustomerId(customerId);

        Map<String, Integer> monthlyRewards = new HashMap<>();

        int totalReward = 0;

        for (CustomerData c : customerData) {
            int points = calculatePoints(c.getAmount());
            String month = c.getDate().getMonth().toString();

            monthlyRewards.put(month, monthlyRewards.getOrDefault(month, 0) + points);
            totalReward += points;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("customerId", customerId);
        response.put("monthlyRewards", monthlyRewards);
        response.put("totalRewards", totalReward);

        return response;

    }


        public int calculatePoints(int amount){
            int points =0;

            if(amount > 100){
                points += (amount -100)*2;

                amount =100;
            }

            if(amount >50){
                points += (amount -50)*1;

            }

            return points;
        }

    public CustomerData saveCustomer(CustomerData customerData) {

        return customerRepository.save(customerData);
    }
}

