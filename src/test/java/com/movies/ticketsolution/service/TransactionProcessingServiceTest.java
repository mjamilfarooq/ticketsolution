package com.movies.ticketsolution.service;

import com.movies.ticketsolution.config.ApplicationConfiguration;
import com.movies.ticketsolution.model.Customer;
import com.movies.ticketsolution.model.TicketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class TransactionProcessingServiceTest {

    List<Customer> testData = new ArrayList<Customer>() {
        {
            add(new Customer("John Woo", 20));
            add(new Customer("Smith Justin", 13));
            add(new Customer("Hulk Hogan", 55));
            add(new Customer("Cristopher Ronaldo", 9));
            add(new Customer("Messi Gureva", 25));
            add(new Customer("Caster Luke", 11));
            add(new Customer("Zac Goldsmith", 66));
            add(new Customer("Justin Dino", 62));
        }
    };

    @Autowired
    private TransactionProcessingService transactionProcessingService;


    @Test
    void process() {
        List<Customer> customers = transactionProcessingService.process(testData);
        assertEquals(8, customers.size());
        assertTrue(customers.contains(new Customer("John Woo", 20, 25.0, TicketType.ADULT)));
        assertTrue(customers.contains(new Customer("Smith Justin", 13, 12.0, TicketType.TEEN)));
        assertTrue(customers.contains(new Customer("Hulk Hogan", 55, 25.0, TicketType.ADULT)));
        assertTrue(customers.contains(new Customer("Cristopher Ronaldo", 9, 5.0, TicketType.CHILDREN)));
        assertTrue(customers.contains(new Customer("Messi Gureva", 25, 25.0, TicketType.ADULT)));
        assertTrue(customers.contains(new Customer("Caster Luke", 11, 5.0, TicketType.CHILDREN)));
        assertTrue(customers.contains(new Customer("Zac Goldsmith", 66, 17.5, TicketType.SENIOR)));
        assertTrue(customers.contains(new Customer("Justin Dino", 62, 25.0, TicketType.ADULT)));


    }
}