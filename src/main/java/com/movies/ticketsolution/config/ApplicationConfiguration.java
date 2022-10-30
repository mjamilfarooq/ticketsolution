package com.movies.ticketsolution.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
public class ApplicationConfiguration{
    @Value("${com.movies.ticket.discountRule.children.percentage: 25.00}")
    private Double discountInPercentage;

    @Value("${com.movies.ticket.discountRule.children.limit: 3}")
    private Integer numberOfChild;

    @Value("${com.movies.ticket.child.maxAge: 11}")
    private Integer childrenMaxAge;

    @Value("${com.movies.ticket.teen.maxAge: 18}")
    private Integer teenMaxAge;

    @Value("${com.movies.ticket.adult.maxAge: 65}")
    private Integer adultMaxAge;

    @Value("${com.movies.ticket.child.cost: 5}")
    private Double childrenCost;

    @Value("${com.movies.ticket.teen.cost: 12}")
    private Double teenCost;

    @Value("${com.movies.ticket.adult.cost: 25}")
    private Double adultCost;

    @Value("${com.movies.ticket.senior.discountInPercentage: 30.00}")
    private Double seniorDiscountPercentage;
}
