package com.movies.ticketsolution.service;

import com.movies.ticketsolution.model.Customer;
import com.movies.ticketsolution.model.Ticket;
import com.movies.ticketsolution.model.TicketType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketGeneratorServiceTest {

    List<Customer> testData = new ArrayList<Customer>() {
        {
            add(new Customer("name1", 0, 25.0, TicketType.ADULT));
            add(new Customer("name2", 0, 15.0, TicketType.ADULT));
            add(new Customer("name1", 0, 12.0, TicketType.TEEN));
            add(new Customer("name2", 0, 13.5, TicketType.TEEN));
            add(new Customer("name1", 0, 25.5, TicketType.SENIOR));
            add(new Customer("name2", 0, 15.5, TicketType.SENIOR));
            add(new Customer("name1", 0, 5.0, TicketType.CHILDREN));
            add(new Customer("name2", 0, 5.0, TicketType.CHILDREN));
        }
    };

    @Test
    public void testGeneratePositive() {
        TicketGeneratorService ticketGeneratorService = new TicketGeneratorService();
        var tickets = ticketGeneratorService.generate(testData);
        assertEquals(4, tickets.size());
        assertEquals(new Ticket(TicketType.ADULT, 2, 40.0), tickets.get(0));
        assertEquals(new Ticket(TicketType.CHILDREN, 2, 10.0), tickets.get(1));
        assertEquals(new Ticket(TicketType.SENIOR, 2, 41.0), tickets.get(2));
        assertEquals(new Ticket(TicketType.TEEN, 2, 25.5), tickets.get(3));
    }

}