package com.movies.ticketsolution.discount;

import com.movies.ticketsolution.model.Customer;

import java.util.List;

public interface IDiscountRule {
    List<Customer> apply(List<Customer> customers);
}
