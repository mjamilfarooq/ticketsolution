package com.movies.ticketsolution.discount;

import com.movies.ticketsolution.config.ApplicationConfiguration;
import com.movies.ticketsolution.model.Customer;
import com.movies.ticketsolution.model.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChildrenDiscountRule implements IDiscountRule {

    private ApplicationConfiguration applicationConfiguration;

    public ChildrenDiscountRule(@Autowired ApplicationConfiguration configuration) {
        applicationConfiguration = configuration;
    }

    @Override
    public List<Customer> apply(List<Customer> customers) {
        return customers
                .stream()
                .collect(Collectors.groupingBy(Customer::getType))
                .entrySet( )
                .stream()
                .peek(cg -> {
                    if (cg.getKey() == TicketType.CHILDREN && cg.getValue().size() >= applicationConfiguration.getNumberOfChild()) {
                        cg.getValue().forEach(c -> c.setCost(c.getCost() - c.getCost() * applicationConfiguration.getDiscountInPercentage() / 100));
                    }
                })
                .flatMap(ticketTypeListEntry -> {
                    return ticketTypeListEntry.getValue().stream();
                })
                .toList();

    }
}
