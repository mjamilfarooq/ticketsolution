package com.movies.ticketsolution.dto;

import com.movies.ticketsolution.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * TransactionResponse contains the same transactionId as provided in the request
 * list of all tickets generated for the customers and total cost of the Tickets.
 */
@Data
@AllArgsConstructor
public class TransactionResponse {
    private Integer transactionId;
    private List<Ticket> tickets;
    private BigDecimal totalCost;

    public TransactionResponse() {
        this.transactionId = null;
        this.tickets = new ArrayList<>();
        this.totalCost = new BigDecimal(0.0).setScale(2);
    }
}
