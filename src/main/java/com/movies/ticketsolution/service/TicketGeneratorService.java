package com.movies.ticketsolution.service;

import com.movies.ticketsolution.model.Customer;
import com.movies.ticketsolution.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketGeneratorService {
    public List<Ticket> generate(List<Customer> customers) {
        return customers
                .stream()
                .collect(Collectors.groupingBy(Customer::getType))
                .entrySet()
                .stream()
                .map(cg -> new Ticket(cg.getKey(),
                        cg.getValue().size(),
                        cg.getValue().stream().map(Customer::getCost).reduce(0.0, Double::sum)))
                .sorted(Comparator.comparing(ticket -> ticket.getTicketType().name()))
                .toList();
    }
}
