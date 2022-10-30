package com.movies.ticketsolution.service;

import com.movies.ticketsolution.config.ApplicationConfiguration;
import com.movies.ticketsolution.discount.IDiscountRule;
import com.movies.ticketsolution.model.Customer;
import com.movies.ticketsolution.model.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProcessingService {

    @Autowired
    private ApplicationConfiguration config;
    @Autowired
    private List<IDiscountRule> discountRules;

    public List<Customer> process(List<Customer> customers) {

        return applyDiscount(customers
                .stream()
                .peek(this::setTicketType)
                .peek(this::setTicketCost)
                .toList());
    }

    private void setTicketType(Customer customer) {
        Integer age = customer.getAge();
        if (age <= config.getChildrenMaxAge()) {
            customer.setType(TicketType.CHILDREN);
        } else if (age > config.getChildrenMaxAge() && age <= config.getTeenMaxAge()) {
            customer.setType(TicketType.TEEN);
        } else if (age > config.getTeenMaxAge() && age <= config.getAdultMaxAge()) {
            customer.setType(TicketType.ADULT);
        } else {
            customer.setType(TicketType.SENIOR);
        }
    }

    private void setTicketCost(Customer customer) {
        TicketType type = customer.getType();
        switch (type) {
            case CHILDREN -> customer.setCost(config.getChildrenCost());
            case TEEN -> customer.setCost(config.getTeenCost());
            case ADULT -> customer.setCost(config.getAdultCost());
            case SENIOR -> customer.setCost(config.getAdultCost() -
                            config.getAdultCost() * config.getSeniorDiscountPercentage() / 100);
        }
    }

    private List<Customer> applyDiscount(List<Customer> customers) {
        for (IDiscountRule rule: discountRules) {
            customers = rule.apply(customers);
        }
        return customers;
    }

}
