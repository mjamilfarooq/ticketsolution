package com.movies.ticketsolution.discount;

import com.movies.ticketsolution.model.Customer;
import com.movies.ticketsolution.model.TicketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChildrenDiscountRuleTest {

    @Autowired
    private ChildrenDiscountRule childrenDiscountRule;

    List<Customer> testData = new ArrayList<Customer>() {
        {
            add(new Customer("name1", 0, 25.0, TicketType.ADULT));
            add(new Customer("name2", 0, 15.0, TicketType.ADULT));
            add(new Customer("name1", 0, 12.0, TicketType.TEEN));
            add(new Customer("name2", 0, 13.5, TicketType.TEEN));
            add(new Customer("name1", 0, 25.5, TicketType.SENIOR));
            add(new Customer("name2", 0, 15.5, TicketType.SENIOR));
            add(new Customer("child1", 0, 55.0, TicketType.CHILDREN));
            add(new Customer("child2", 0, 50.0, TicketType.CHILDREN));
            add(new Customer("child3", 0, 60.0, TicketType.CHILDREN));
        }
    };

    @Test
    void applyPositive() {
        List<Customer> customers = childrenDiscountRule.apply(testData);
        assertTrue(customers.contains(new Customer("child1", 0, 55-55*0.25, TicketType.CHILDREN)));
        assertTrue(customers.contains(new Customer("child2", 0, 50-50*0.25, TicketType.CHILDREN)));
        assertTrue(customers.contains(new Customer("child3", 0, 60-60*0.25, TicketType.CHILDREN)));


    }

    @Test
    void applyNegative() {
        List<Customer> customers = childrenDiscountRule.apply(testData.subList(0, 8));
        assertTrue(customers.contains(new Customer("child1", 0, 55.0, TicketType.CHILDREN)));
        assertTrue(customers.contains(new Customer("child2", 0, 50.0, TicketType.CHILDREN)));

    }
}