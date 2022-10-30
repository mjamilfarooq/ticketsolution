package com.movies.ticketsolution.controller;

import com.movies.ticketsolution.dto.TransactionRequest;
import com.movies.ticketsolution.dto.TransactionResponse;
import com.movies.ticketsolution.model.Ticket;
import com.movies.ticketsolution.service.TransactionProcessingService;
import com.movies.ticketsolution.service.TicketGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Provide API interface to generate ticket
 */
@RestController
public class TransactionController {
    @Autowired
    TicketGeneratorService ticketGeneratorService;

    @Autowired
    TransactionProcessingService transactionProcessingService;

    /**
     * @param transactionRequest contains all the customers and transaction id
     * @return #{@code TransactionResponse} containing list of tickets and total cost of transaction
     */
    @PostMapping("/process")
    public TransactionResponse process(@RequestBody TransactionRequest transactionRequest) {
        List<Ticket> tickets = ticketGeneratorService.generate(transactionProcessingService.process(transactionRequest.getCustomers()));
        return new TransactionResponse(transactionRequest.getTransactionId(),
                tickets,
                tickets
                        .stream()
                        .map(Ticket::getTotalCost)
                        .reduce(new BigDecimal(0.00), BigDecimal::add));
    }
}
