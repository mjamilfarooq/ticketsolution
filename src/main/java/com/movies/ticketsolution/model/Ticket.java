package com.movies.ticketsolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private TicketType ticketType;
    private Integer quantity;
    private Double totalCost;

    public BigDecimal getTotalCost() {
        return new BigDecimal(totalCost).setScale(2);
    }
}
